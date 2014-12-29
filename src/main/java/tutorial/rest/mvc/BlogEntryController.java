package tutorial.rest.mvc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tutorial.core.services.BlogEntryService;
import tutorial.core.models.entities.BlogEntry;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;


/**
 * Created by Anika McRae on 17/12/2014.
 */
@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {

    private BlogEntryService service;

    @Autowired
    public BlogEntryController(BlogEntryService service) {
        this.service = service;
    }



    @RequestMapping(value="/{blogEntryId}", method = RequestMethod.GET)
    public ResponseEntity<BlogEntryResource> getBlogEntry(@PathVariable Long blogEntryId) {
        BlogEntry entry = service.findBlogEntry(blogEntryId);

        if (entry != null) {
            BlogEntryResource res = new BlogEntryResourceAsm().toResource(entry);
            return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }



    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.DELETE)
    public ResponseEntity<BlogEntryResource> deleteBlogEntry(@PathVariable Long blogEntryId) {
        BlogEntry blogEntry = service.deleteBlogEntry(blogEntryId);

        if (blogEntry != null) {
            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(blogEntry);
            return new ResponseEntity<BlogEntryResource>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }



    @RequestMapping(value = "/{blogEntryId}", method = RequestMethod.PUT)
    public ResponseEntity<BlogEntryResource> updateBlogEntry(@PathVariable Long blogEntryId, @RequestBody BlogEntryResource sentBlogEntry) {
        BlogEntry updatedBlogEntry = service.updateBlogEntry(blogEntryId, sentBlogEntry.toBlogEntry());

        if (updatedBlogEntry != null) {
            BlogEntryResource resource = new BlogEntryResourceAsm().toResource(updatedBlogEntry);
            return new ResponseEntity<BlogEntryResource>(resource, HttpStatus.OK);
        } else {
            return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
        }
    }



}
