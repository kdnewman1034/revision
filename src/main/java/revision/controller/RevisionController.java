package revision.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import revision.exception.RevisionException;
import revision.service.IRevisionCompareService;

@RestController
public class RevisionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RevisionController.class);
	
	@Autowired
	private IRevisionCompareService revisionCompareService;
	
	@GetMapping("/revisionCompare")
	public String compareRevision(@RequestParam String revisionOne, @RequestParam String revisionTwo) {
		
		try {
			return revisionCompareService.compareRevision(revisionOne, revisionTwo);
		} catch (RevisionException e) {
			LOGGER.error("Error comparing revisions", e);
			return "error";
		}
	}

}
