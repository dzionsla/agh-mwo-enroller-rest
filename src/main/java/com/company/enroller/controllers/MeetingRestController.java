package com.company.enroller.controllers;

import java.util.Collection;

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
	public ResponseEntity<?> getMeetings() {
		Collection<Meeting> meetings = meetingService.getAll();
		return new ResponseEntity<Collection<Meeting>>(meetings, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/participants", method = RequestMethod.POST) // pkt 4 !!
	public ResponseEntity<?> addParticipantToMeeting(@PathVariable("id") int id, @RequestBody Participant newParticipant) {
		// pobrać spotkanie
		// pobrać uczestnika
		
		// dodac uczestnika do spotkania
		
		// pracujemy na dwich autowired, meeting i service!
		
		
		return null;
		
	}
	
	// pkt 5 - pobieramy spotkanie, getParticipants i the end
	
	
	// GOLD !!
	// usuwamy tylko spotkanie, nie użytkowników
	// 2. banalne ponoc
	// 3. jak dodawanie tylko na odwrót "/{id}/participants/{participantId}"
	
	// premium
	// 1. Koncepcja korzystania z uslugi REST - @RequestParam, sortowanie po stronie SQL  
	// 2.
	// 3.
	
//	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> getParticipant(@PathVariable("id") String login) {
//	     Participant participant = participantService.findByLogin(login);
//	     if (participant == null) {
//	         return new ResponseEntity(HttpStatus.NOT_FOUND);
//	     }
//	     return new ResponseEntity<Participant>(participant, HttpStatus.OK);
//	 }
//	
//	 @RequestMapping(value = "", method = RequestMethod.POST)
//	 public ResponseEntity<?> registerParticipant(@RequestBody Participant participant)
//	 {
//		 Participant foundParticipant = participantService.findByLogin(participant.getLogin());
//	     if (participant == null) {
//	    	 return new ResponseEntity("Unable to create. A participant with login " + participant.getLogin() + " already exist.", HttpStatus.CONFLICT);
//	     }
//	     participantService.add(participant);
//	     return new ResponseEntity<Participant>(participant, HttpStatus.CREATED);
//		 
//	 }
//	 	
//	 @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	 public ResponseEntity<?> deleteParticipant(@PathVariable("id") String login)
//	 {
//		 Participant participant = participantService.findByLogin(login);
//	     if (participant == null) {
//	    	 return new ResponseEntity(HttpStatus.NOT_FOUND);
//	     }
//	     participantService.delete(participant);
//	     return new ResponseEntity<Participant>(participant, HttpStatus.OK);
//	     //return new ResponseEntity<Participant>(HttpStatus.NO_CONTENT);
//		 
//	 }
//
//	 @RequestMapping(value = "{id}", method = RequestMethod.PUT) // tylko zmiana hasła
//	 public ResponseEntity<?> updateParticipant(@PathVariable("id") String login, @RequestBody Participant incomingParticipant)
//	 {
//		 
//		 Participant participant = participantService.findByLogin(login);
//	     if (participant == null) {
//	    	 return new ResponseEntity(HttpStatus.NOT_FOUND);
//	     }
//	     participant.setPassword(incomingParticipant.getPassword());
//	     participantService.update(participant);
//	     return new ResponseEntity<Participant>(participant, HttpStatus.OK);
//	     //return new ResponseEntity<Participant>(HttpStatus.NO_CONTENT);
//		 
//	 }
//	 
	 // pkt 4 i 5, dodanie endpointa  RequestMapping(value = "{meetingid}/participants", method = RequestMethod.PUT)
	 // dla danego spotkania "meetings/2/participants" i robie POST na to!!
}
