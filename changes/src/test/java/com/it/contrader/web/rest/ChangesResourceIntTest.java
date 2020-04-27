package com.it.contrader.web.rest;

import com.it.contrader.ChangesApp;

import com.it.contrader.domain.Changes;
import com.it.contrader.repository.ChangesRepository;
import com.it.contrader.service.ChangesService;
import com.it.contrader.service.dto.ChangesDTO;
import com.it.contrader.service.mapper.ChangesMapper;
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
 * Test class for the ChangesResource REST controller.
 *
 * @see ChangesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChangesApp.class)
public class ChangesResourceIntTest {

    private static final Long DEFAULT_USER = 1L;
    private static final Long UPDATED_USER = 2L;

    private static final String DEFAULT_TAG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TAG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REMOVED_TAG = "AAAAAAAAAA";
    private static final String UPDATED_REMOVED_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_TAG_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_TAG_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_CHANGES_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CHANGES_NAME = "BBBBBBBBBB";

    @Autowired
    private ChangesRepository changesRepository;


    @Autowired
    private ChangesMapper changesMapper;
    

    @Autowired
    private ChangesService changesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChangesMockMvc;

    private Changes changes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChangesResource changesResource = new ChangesResource(changesService);
        this.restChangesMockMvc = MockMvcBuilders.standaloneSetup(changesResource)
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
    public static Changes createEntity(EntityManager em) {
        Changes changes = new Changes()
            .user(DEFAULT_USER)
            .tag_name(DEFAULT_TAG_NAME)
            .removed_tag(DEFAULT_REMOVED_TAG)
            .tag_position(DEFAULT_TAG_POSITION)
            .changes_name(DEFAULT_CHANGES_NAME);
        return changes;
    }

    @Before
    public void initTest() {
        changes = createEntity(em);
    }

    @Test
    @Transactional
    public void createChanges() throws Exception {
        int databaseSizeBeforeCreate = changesRepository.findAll().size();

        // Create the Changes
        ChangesDTO changesDTO = changesMapper.toDto(changes);
        restChangesMockMvc.perform(post("/api/changes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(changesDTO)))
            .andExpect(status().isCreated());

        // Validate the Changes in the database
        List<Changes> changesList = changesRepository.findAll();
        assertThat(changesList).hasSize(databaseSizeBeforeCreate + 1);
        Changes testChanges = changesList.get(changesList.size() - 1);
        assertThat(testChanges.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testChanges.getTag_name()).isEqualTo(DEFAULT_TAG_NAME);
        assertThat(testChanges.getRemoved_tag()).isEqualTo(DEFAULT_REMOVED_TAG);
        assertThat(testChanges.getTag_position()).isEqualTo(DEFAULT_TAG_POSITION);
        assertThat(testChanges.getChanges_name()).isEqualTo(DEFAULT_CHANGES_NAME);
    }

    @Test
    @Transactional
    public void createChangesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = changesRepository.findAll().size();

        // Create the Changes with an existing ID
        changes.setId(1L);
        ChangesDTO changesDTO = changesMapper.toDto(changes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChangesMockMvc.perform(post("/api/changes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(changesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Changes in the database
        List<Changes> changesList = changesRepository.findAll();
        assertThat(changesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkUserIsRequired() throws Exception {
        int databaseSizeBeforeTest = changesRepository.findAll().size();
        // set the field null
        changes.setUser(null);

        // Create the Changes, which fails.
        ChangesDTO changesDTO = changesMapper.toDto(changes);

        restChangesMockMvc.perform(post("/api/changes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(changesDTO)))
            .andExpect(status().isBadRequest());

        List<Changes> changesList = changesRepository.findAll();
        assertThat(changesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChanges_nameIsRequired() throws Exception {
        int databaseSizeBeforeTest = changesRepository.findAll().size();
        // set the field null
        changes.setChanges_name(null);

        // Create the Changes, which fails.
        ChangesDTO changesDTO = changesMapper.toDto(changes);

        restChangesMockMvc.perform(post("/api/changes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(changesDTO)))
            .andExpect(status().isBadRequest());

        List<Changes> changesList = changesRepository.findAll();
        assertThat(changesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllChanges() throws Exception {
        // Initialize the database
        changesRepository.saveAndFlush(changes);

        // Get all the changesList
        restChangesMockMvc.perform(get("/api/changes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(changes.getId().intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.intValue())))
            .andExpect(jsonPath("$.[*].tag_name").value(hasItem(DEFAULT_TAG_NAME.toString())))
            .andExpect(jsonPath("$.[*].removed_tag").value(hasItem(DEFAULT_REMOVED_TAG.toString())))
            .andExpect(jsonPath("$.[*].tag_position").value(hasItem(DEFAULT_TAG_POSITION.toString())))
            .andExpect(jsonPath("$.[*].changes_name").value(hasItem(DEFAULT_CHANGES_NAME.toString())));
    }
    

    @Test
    @Transactional
    public void getChanges() throws Exception {
        // Initialize the database
        changesRepository.saveAndFlush(changes);

        // Get the changes
        restChangesMockMvc.perform(get("/api/changes/{id}", changes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(changes.getId().intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.intValue()))
            .andExpect(jsonPath("$.tag_name").value(DEFAULT_TAG_NAME.toString()))
            .andExpect(jsonPath("$.removed_tag").value(DEFAULT_REMOVED_TAG.toString()))
            .andExpect(jsonPath("$.tag_position").value(DEFAULT_TAG_POSITION.toString()))
            .andExpect(jsonPath("$.changes_name").value(DEFAULT_CHANGES_NAME.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingChanges() throws Exception {
        // Get the changes
        restChangesMockMvc.perform(get("/api/changes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChanges() throws Exception {
        // Initialize the database
        changesRepository.saveAndFlush(changes);

        int databaseSizeBeforeUpdate = changesRepository.findAll().size();

        // Update the changes
        Changes updatedChanges = changesRepository.findById(changes.getId()).get();
        // Disconnect from session so that the updates on updatedChanges are not directly saved in db
        em.detach(updatedChanges);
        updatedChanges
            .user(UPDATED_USER)
            .tag_name(UPDATED_TAG_NAME)
            .removed_tag(UPDATED_REMOVED_TAG)
            .tag_position(UPDATED_TAG_POSITION)
            .changes_name(UPDATED_CHANGES_NAME);
        ChangesDTO changesDTO = changesMapper.toDto(updatedChanges);

        restChangesMockMvc.perform(put("/api/changes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(changesDTO)))
            .andExpect(status().isOk());

        // Validate the Changes in the database
        List<Changes> changesList = changesRepository.findAll();
        assertThat(changesList).hasSize(databaseSizeBeforeUpdate);
        Changes testChanges = changesList.get(changesList.size() - 1);
        assertThat(testChanges.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testChanges.getTag_name()).isEqualTo(UPDATED_TAG_NAME);
        assertThat(testChanges.getRemoved_tag()).isEqualTo(UPDATED_REMOVED_TAG);
        assertThat(testChanges.getTag_position()).isEqualTo(UPDATED_TAG_POSITION);
        assertThat(testChanges.getChanges_name()).isEqualTo(UPDATED_CHANGES_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingChanges() throws Exception {
        int databaseSizeBeforeUpdate = changesRepository.findAll().size();

        // Create the Changes
        ChangesDTO changesDTO = changesMapper.toDto(changes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException 
        restChangesMockMvc.perform(put("/api/changes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(changesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Changes in the database
        List<Changes> changesList = changesRepository.findAll();
        assertThat(changesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteChanges() throws Exception {
        // Initialize the database
        changesRepository.saveAndFlush(changes);

        int databaseSizeBeforeDelete = changesRepository.findAll().size();

        // Get the changes
        restChangesMockMvc.perform(delete("/api/changes/{id}", changes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Changes> changesList = changesRepository.findAll();
        assertThat(changesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Changes.class);
        Changes changes1 = new Changes();
        changes1.setId(1L);
        Changes changes2 = new Changes();
        changes2.setId(changes1.getId());
        assertThat(changes1).isEqualTo(changes2);
        changes2.setId(2L);
        assertThat(changes1).isNotEqualTo(changes2);
        changes1.setId(null);
        assertThat(changes1).isNotEqualTo(changes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChangesDTO.class);
        ChangesDTO changesDTO1 = new ChangesDTO();
        changesDTO1.setId(1L);
        ChangesDTO changesDTO2 = new ChangesDTO();
        assertThat(changesDTO1).isNotEqualTo(changesDTO2);
        changesDTO2.setId(changesDTO1.getId());
        assertThat(changesDTO1).isEqualTo(changesDTO2);
        changesDTO2.setId(2L);
        assertThat(changesDTO1).isNotEqualTo(changesDTO2);
        changesDTO1.setId(null);
        assertThat(changesDTO1).isNotEqualTo(changesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(changesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(changesMapper.fromId(null)).isNull();
    }
}
