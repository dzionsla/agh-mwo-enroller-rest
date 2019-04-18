package com.company.enroller.controllers;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.company.enroller.model.Meeting;
import com.company.enroller.model.Participant;
import com.company.enroller.persistence.MeetingService;
import com.company.enroller.persistence.ParticipantService;

@RestController
@RequestMapping("/meetings")  // obsługa uczestnikow, dodawanie, usuwanie itp
public class MeetingRestController {

	@Autowired
	MeetingService meetingService;
	@Autowired
	ParticipantService participantService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> getMeetings() 
	{
		Collection<Meeting> meetings = meetingService.getAll();
		return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getMeeting(@PathVariable("id") long id) {
		Meeting meeting = meetingService.findByID(id);
	    if (meeting == null) 
	    {
	    	return new ResponseEntity(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	 }
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> registerMeeting(@RequestBody Meeting meeting)
	{
		 Meeting foundMeeting = meetingService.findByID(meeting.getId());
		 if (foundMeeting != null) 
		 {
			 return new ResponseEntity("Unable to create the meeting. A meeting with Id " + meeting.getId() + " already exist.", HttpStatus.CONFLICT);
		 }
	     meetingService.add(meeting);
	     return new ResponseEntity<Meeting>(meeting, HttpStatus.CREATED);
	}
	
	 @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteMeeting(@PathVariable("id") long id)
	{
		 Meeting meeting = meetingService.findByID(id);
	     if (meeting == null) 
	     {
	    	 return new ResponseEntity(HttpStatus.NOT_FOUND);
	     }
	     meetingService.delete(meeting);
	     return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/meetings/{participantId}", method = RequestMethod.PUT)
	public ResponseEntity<?> addParticipantToMeeting(@PathVariable("id") Long id, @PathVariable("participantId") String login) {
		Meeting meeting = meetingService.findByID(id);
		Participant participant = participantService.findByLogin(login);
		if (meeting == null || participant == null) {
			return new ResponseEntity("Unable to update. A meeting with id " + id + " does not exist.", HttpStatus.NOT_FOUND);
		} 
		meetingService.addParticipantToTheMeeting(id, participant);	
		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
	}	 
	 
//	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
//	public ResponseEntity<?> addParticipantToMeeting(@PathVariable("id") long id, @RequestBody Participant newParticipant) {
//		Meeting meeting = meetingService.findByID(id);
//		if (meeting == null) 
//		{
//	    	 return new ResponseEntity(HttpStatus.NOT_FOUND);
//	    }
//		Participant foundParticipant = participantService.findByLogin(newParticipant.getLogin());
//	    if (foundParticipant == null) 
//	    {
//	    	return new ResponseEntity("Unable to create. A participant with login " + newParticipant.getLogin() + " already exist.", HttpStatus.CONFLICT);
//	    }
//	    System.out.println(meeting.getId());
//	    System.out.println(newParticipant.getLogin());
//	    meetingService.addParticipantToTheMeeting(meeting.getId(), newParticipant);
//	    return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
//	}
	
//	@RequestMapping(value = "/{id}/participants", method = RequestMethod.GET) // pkt 4 !!
//	public ResponseEntity<?> getParticipantsFromMeeting(@PathVariable("id") int id) {
//		// pobrać spotkanie
//		Meeting meeting = meetingService.findByID(id);
//		if (meeting == null) {
//	    	 return new ResponseEntity(HttpStatus.NOT_FOUND);
//	    }
//
//		//Collection<Participant> participants = meeting.getParticipants();
//		System.out.println(meeting.getId());
//	    //return new ResponseEntity<Collection<Participant>>(participants, HttpStatus.OK);
//		return new ResponseEntity<Meeting>(meeting, HttpStatus.OK);
//	}
	// pkt 5 - pobieramy spotkanie, getParticipants i the end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// GOLD !!
	// usuwamy tylko spotkanie, nie użytkowników
	// 2. banalne ponoc
	// 3. jak dodawanie tylko na odwrót "/{id}/participants/{participantId}"
	
	// premium
	// 1. Koncepcja korzystania z uslugi REST - @RequestParam, sortowanie po stronie SQL  
	// 2.
	// 3.
	
	 // pkt 4 i 5, dodanie endpointa  RequestMapping(value = "{meetingid}/participants", method = RequestMethod.PUT)
	 // dla danego spotkania "meetings/2/participants" i robie POST na to!!
}
