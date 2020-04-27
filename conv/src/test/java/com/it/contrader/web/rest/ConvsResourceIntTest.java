package com.it.contrader.web.rest;

import com.it.contrader.ConvApp;

import com.it.contrader.domain.Convs;
import com.it.contrader.repository.ConvsRepository;
import com.it.contrader.service.ConvsService;
import com.it.contrader.service.dto.ConvsDTO;
import com.it.contrader.service.mapper.ConvsMapper;
import com.it.contrader.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static com.it.contrader.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConvsResource REST controller.
 *
 * @see ConvsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConvApp.class)
public class ConvsResourceIntTest {

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCETYPE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCETYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OUTPUTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_OUTPUTTYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_USR = 1L;
    private static final Long UPDATED_USR = 2L;

    private static final String DEFAULT_TAGNAME = "AAAAAAAAAA";
    private static final String UPDATED_TAGNAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMOVEDTAG = "AAAAAAAAAA";
    private static final String UPDATED_REMOVEDTAG = "BBBBBBBBBB";

    private static final String DEFAULT_TAGPOSITION = "AAAAAAAAAA";
    private static final String UPDATED_TAGPOSITION = "BBBBBBBBBB";

    @Autowired
    private ConvsRepository convsRepository;


    @Autowired
    private ConvsMapper convsMapper;
    

    @Autowired
    private ConvsService convsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConvsMockMvc;

    private Convs convs;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConvsResource convsResource = new ConvsResource(convsService);
        this.restConvsMockMvc = MockMvcBuilders.standaloneSetup(convsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Convs createEntity(EntityManager em) {
        Convs convs = new Convs()
            .source(DEFAULT_SOURCE)
            .sourcetype(DEFAULT_SOURCETYPE)
            .outputtype(DEFAULT_OUTPUTTYPE)
            .usr(DEFAULT_USR)
            .tagname(DEFAULT_TAGNAME)
            .removedtag(DEFAULT_REMOVEDTAG)
            .tagposition(DEFAULT_TAGPOSITION);
        return convs;
    }

    @Before
    public void initTest() {
        convs = createEntity(em);
    }

    @Test
    @Transactional
    public void createConvs() throws Exception {
        int databaseSizeBeforeCreate = convsRepository.findAll().size();

        // Create the Convs
        ConvsDTO convsDTO = convsMapper.toDto(convs);
        restConvsMockMvc.perform(post("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isCreated());

        // Validate the Convs in the database
        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeCreate + 1);
        Convs testConvs = convsList.get(convsList.size() - 1);
        assertThat(testConvs.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testConvs.getSourcetype()).isEqualTo(DEFAULT_SOURCETYPE);
        assertThat(testConvs.getOutputtype()).isEqualTo(DEFAULT_OUTPUTTYPE);
        assertThat(testConvs.getUsr()).isEqualTo(DEFAULT_USR);
        assertThat(testConvs.getTagname()).isEqualTo(DEFAULT_TAGNAME);
        assertThat(testConvs.getRemovedtag()).isEqualTo(DEFAULT_REMOVEDTAG);
        assertThat(testConvs.getTagposition()).isEqualTo(DEFAULT_TAGPOSITION);
    }

    @Test
    @Transactional
    public void createConvsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = convsRepository.findAll().size();

        // Create the Convs with an existing ID
        convs.setId(1L);
        ConvsDTO convsDTO = convsMapper.toDto(convs);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConvsMockMvc.perform(post("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Convs in the database
        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = convsRepository.findAll().size();
        // set the field null
        convs.setSource(null);

        // Create the Convs, which fails.
        ConvsDTO convsDTO = convsMapper.toDto(convs);

        restConvsMockMvc.perform(post("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isBadRequest());

        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSourcetypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = convsRepository.findAll().size();
        // set the field null
        convs.setSourcetype(null);

        // Create the Convs, which fails.
        ConvsDTO convsDTO = convsMapper.toDto(convs);

        restConvsMockMvc.perform(post("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isBadRequest());

        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOutputtypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = convsRepository.findAll().size();
        // set the field null
        convs.setOutputtype(null);

        // Create the Convs, which fails.
        ConvsDTO convsDTO = convsMapper.toDto(convs);

        restConvsMockMvc.perform(post("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isBadRequest());

        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = convsRepository.findAll().size();
        // set the field null
        convs.setUsr(null);

        // Create the Convs, which fails.
        ConvsDTO convsDTO = convsMapper.toDto(convs);

        restConvsMockMvc.perform(post("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isBadRequest());

        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConvs() throws Exception {
        // Initialize the database
        convsRepository.saveAndFlush(convs);

        // Get all the convsList
        restConvsMockMvc.perform(get("/api/convs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(convs.getId().intValue())))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].sourcetype").value(hasItem(DEFAULT_SOURCETYPE.toString())))
            .andExpect(jsonPath("$.[*].outputtype").value(hasItem(DEFAULT_OUTPUTTYPE.toString())))
            .andExpect(jsonPath("$.[*].usr").value(hasItem(DEFAULT_USR.intValue())))
            .andExpect(jsonPath("$.[*].tagname").value(hasItem(DEFAULT_TAGNAME.toString())))
            .andExpect(jsonPath("$.[*].removedtag").value(hasItem(DEFAULT_REMOVEDTAG.toString())))
            .andExpect(jsonPath("$.[*].tagposition").value(hasItem(DEFAULT_TAGPOSITION.toString())));
    }
    

    @Test
    @Transactional
    public void getConvs() throws Exception {
        // Initialize the database
        convsRepository.saveAndFlush(convs);

        // Get the convs
        restConvsMockMvc.perform(get("/api/convs/{id}", convs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(convs.getId().intValue()))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.sourcetype").value(DEFAULT_SOURCETYPE.toString()))
            .andExpect(jsonPath("$.outputtype").value(DEFAULT_OUTPUTTYPE.toString()))
            .andExpect(jsonPath("$.usr").value(DEFAULT_USR.intValue()))
            .andExpect(jsonPath("$.tagname").value(DEFAULT_TAGNAME.toString()))
            .andExpect(jsonPath("$.removedtag").value(DEFAULT_REMOVEDTAG.toString()))
            .andExpect(jsonPath("$.tagposition").value(DEFAULT_TAGPOSITION.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingConvs() throws Exception {
        // Get the convs
        restConvsMockMvc.perform(get("/api/convs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConvs() throws Exception {
        // Initialize the database
        convsRepository.saveAndFlush(convs);

        int databaseSizeBeforeUpdate = convsRepository.findAll().size();

        // Update the convs
        Convs updatedConvs = convsRepository.findById(convs.getId()).get();
        // Disconnect from session so that the updates on updatedConvs are not directly saved in db
        em.detach(updatedConvs);
        updatedConvs
            .source(UPDATED_SOURCE)
            .sourcetype(UPDATED_SOURCETYPE)
            .outputtype(UPDATED_OUTPUTTYPE)
            .usr(UPDATED_USR)
            .tagname(UPDATED_TAGNAME)
            .removedtag(UPDATED_REMOVEDTAG)
            .tagposition(UPDATED_TAGPOSITION);
        ConvsDTO convsDTO = convsMapper.toDto(updatedConvs);

        restConvsMockMvc.perform(put("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isOk());

        // Validate the Convs in the database
        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeUpdate);
        Convs testConvs = convsList.get(convsList.size() - 1);
        assertThat(testConvs.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testConvs.getSourcetype()).isEqualTo(UPDATED_SOURCETYPE);
        assertThat(testConvs.getOutputtype()).isEqualTo(UPDATED_OUTPUTTYPE);
        assertThat(testConvs.getUsr()).isEqualTo(UPDATED_USR);
        assertThat(testConvs.getTagname()).isEqualTo(UPDATED_TAGNAME);
        assertThat(testConvs.getRemovedtag()).isEqualTo(UPDATED_REMOVEDTAG);
        assertThat(testConvs.getTagposition()).isEqualTo(UPDATED_TAGPOSITION);
    }

    @Test
    @Transactional
    public void updateNonExistingConvs() throws Exception {
        int databaseSizeBeforeUpdate = convsRepository.findAll().size();

        // Create the Convs
        ConvsDTO convsDTO = convsMapper.toDto(convs);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restConvsMockMvc.perform(put("/api/convs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(convsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Convs in the database
        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteConvs() throws Exception {
        // Initialize the database
        convsRepository.saveAndFlush(convs);

        int databaseSizeBeforeDelete = convsRepository.findAll().size();

        // Get the convs
        restConvsMockMvc.perform(delete("/api/convs/{id}", convs.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Convs> convsList = convsRepository.findAll();
        assertThat(convsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Convs.class);
        Convs convs1 = new Convs();
        convs1.setId(1L);
        Convs convs2 = new Convs();
        convs2.setId(convs1.getId());
        assertThat(convs1).isEqualTo(convs2);
        convs2.setId(2L);
        assertThat(convs1).isNotEqualTo(convs2);
        convs1.setId(null);
        assertThat(convs1).isNotEqualTo(convs2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConvsDTO.class);
        ConvsDTO convsDTO1 = new ConvsDTO();
        convsDTO1.setId(1L);
        ConvsDTO convsDTO2 = new ConvsDTO();
        assertThat(convsDTO1).isNotEqualTo(convsDTO2);
        convsDTO2.setId(convsDTO1.getId());
        assertThat(convsDTO1).isEqualTo(convsDTO2);
        convsDTO2.setId(2L);
        assertThat(convsDTO1).isNotEqualTo(convsDTO2);
        convsDTO1.setId(null);
        assertThat(convsDTO1).isNotEqualTo(convsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(convsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(convsMapper.fromId(null)).isNull();
    }
}
