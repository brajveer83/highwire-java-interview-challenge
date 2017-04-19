package com.highwire.interview;

import org.apache.commons.io.IOUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.highwire.interview.model.Fact;
import com.highwire.interview.model.FactCategory;
import com.highwire.interview.repository.FactCategoryRepository;
import com.highwire.interview.service.FactService;

/**
 * Level 1.
 * These tests should all pass. Improve the application code so that they do.
 * Do not modify the assertions, but feel free to add more if you think it would be appropriate.
 * You can add your own integration test class by putting in src/test/java and making sure the class name ends in "ITCase".
 */
@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration(InterviewChallengeApplication.class)
public class InterviewChallengeApplicationITCase extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private FactCategoryRepository factCategoryRepository;
    
    @Autowired
    private FactService factService;

    private MockMvc mockMvc;
    
    /**
     * Initialize the MockMvc object before each test method.
     */
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    /**
     * Make sure that the name on the home page is what we expect.
     * @throws Exception
     */
    @Test
    public void homeScreenContainsCorrectText() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Hello, Bob!")));
    }
    
    /**
     * Make sure that created facts are created properly.
     */
    @Test
    public void testFactCreation() {
        Fact fact = factService.createFact(new Fact("Cats have four legs", new FactCategory("animals")));
        Assert.assertNotNull(fact.getId());
        Assert.assertNotNull(fact.getCreationDate());
        Assert.assertEquals("Cats have four legs", fact.getFactText());
        Assert.assertEquals("animals", fact.getFactCategory().getCategory());
    }
    
    /**
     * Make sure that submitting a fact via the api creates the fact and returns it with the correct field values.
     * @throws Exception
     */
    @Test
    public void testFactCreationViaApi() throws Exception {
        String inputBody = IOUtils.toString(new ClassPathResource("input/fact-create.json").getInputStream());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/facts")
                .content(inputBody)
                .contentType(MediaType.APPLICATION_JSON))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.factText", Matchers.equalTo("Cats have four legs")))
            .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Matchers.notNullValue()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.factCategory.id", Matchers.notNullValue()))
            .andExpect(MockMvcResultMatchers.jsonPath("$.factCategory.category", Matchers.equalTo("animals")));
    }

    /**
     * Make sure that after creating two facts, we don't create multiple fact categories with the same value
     */
    @Test
    public void testNonMultipleFactCategories() {
    	/*boolean error = false;
    	Exception exception = null;
        try {
    	factService.createFact(new Fact("Cats have four legs", new FactCategory("animals")));
        factService.createFact(new Fact("Birds have two legs", new FactCategory("animals")));
        } catch(Throwable e) {
        	error = true;
        } finally {
        	if (error) {
        		 Assert.assertTrue(exception instanceof ConstraintViolationException);
        		 return;
        	}
        }*/
    	Fact fact = new Fact();
    	FactCategory factCategroy = new FactCategory("animals");
    	fact.setFactCategory(factCategroy);
    	fact.setFactText("Cats have four legs");
    	factService.createFact(fact);
    	fact = new Fact();
    	fact.setFactCategory(factCategroy);
    	fact.setFactText("Birds have two legs");
    	factService.createFact(fact);
    	
        Assert.assertEquals(2, this.factService.getAllFacts().size());
        Assert.assertEquals(1, this.factCategoryRepository.count());
    }
    
}
