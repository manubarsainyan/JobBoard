package edu.sjsu.cmpe275.mail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import edu.sjsu.cmpe275.companyPositions.CompanyPosition;
import edu.sjsu.cmpe275.companyprofile.CompanyProfile;
import edu.sjsu.cmpe275.user.UserProfile;

@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
 
    @Override
    public void sendRegistrationEmail(UserProfile user) {
    	
    	String message="Hi, " 
                        + " thank you for registering please use the validation code "+ user.getUserId();
        MimeMessagePreparator preparator = getMessagePreparator(user.getEmail(),message," Validation code");
 
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public void sendApplicationEmail(CompanyPosition position,UserProfile user) {
    	String message="Hi, " 
                + " thank you for applying for "+position.getPosition_title()+" Position details are:"
                		+ " \n Position title: "+ position.getPosition_title()+
                		"\n Position description: "+position.getPosition_description()+
                		"\n Location: "+position.getOffice_location()+
                		"\n Date applied on: "+ new Date();
    	
    	MimeMessagePreparator preparator = getMessagePreparator(user.getEmail(),message,"Job Application details");

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
	        }
	    }
    
    @Override
    public void sendRejectionEmail(CompanyPosition position,UserProfile user) {
    	String message="Hi, " 
                + " thank you for applying for "+position.getPosition_title()
                		+ " \n Position title: "+ position.getPosition_title()+
                		"\n Position description: "+position.getPosition_description()+
                		"\n Location: "+position.getOffice_location()+
                		"\n Date applied on: "+ new Date()+
    					"Unfortunately, we are not moving forward with your candidacy. However, we are keeping your resume in our database and will contact for any future matching";
    	
    	MimeMessagePreparator preparator = getMessagePreparator(user.getEmail(),message,"Job Application details");

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
	        }
	    }
    
    public void sendAcceptanceEmail(CompanyPosition position, UserProfile user){
    	String message="Hi, " 
                + " thank you for applying for "+position.getPosition_title()
                		+ " \n Position title: "+ position.getPosition_title()+
                		"\n Position description: "+position.getPosition_description()+
                		"\n Location: "+position.getOffice_location()+
                		"\n Date applied on: "+ new Date()+
    					"Congratulations, you are selected for the this position. We will mail you offer letter shortly.";
    	
    	MimeMessagePreparator preparator = getMessagePreparator(user.getEmail(),message,"Job Application details");

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
	        }
	    }
    
    
    @Override
    public void sendUnmarkOnCloseEmail(CompanyPosition position,UserProfile user) {
    	String message="Hi, " 
                + " This is to notify that "+position.getPosition_title()+" is Either filled or closed and is removed from your Marked List.  Position details are:"
                		+ " \n Position title: "+ position.getPosition_title()+
                		"\n Position description: "+position.getPosition_description()+
                		"\n Location: "+position.getOffice_location()+
                		"\n Date applied on: "+ new Date();
    	
    	MimeMessagePreparator preparator = getMessagePreparator(user.getEmail(),message,"Job Application details");

        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
	        }
	    }
    
    @Override
    public void sendPositionEmail(CompanyPosition position,UserProfile user) {
    	String message="Hi, " 
                + " This is to notify that  "+position.getPosition_title()+" has been updated. Position details are:"
                		+ " \n Position title: "+ position.getPosition_title()+
                		"\n Position description: "+position.getPosition_description()+
                		"\n Location: "+position.getOffice_location()+
                		"\n Position responsibilities: "+ position.getPosition_responsibility()+
                		"\n Salary : "+position.getSalary();
    	
    	MimeMessagePreparator preparator = getMessagePreparator(user.getEmail(),message,"Job Positions have been updated");       
        try {
            mailSender.send(preparator);
            System.out.println("Message Send...Hurrey");
        } catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
 
    private MimeMessagePreparator getMessagePreparator2(final String email,final String message,final String subject, Multipart multipart) {
      	 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress("mbarsainyan21@gmail.com"));
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(email));
                mimeMessage.setContent(multipart);
                mimeMessage.setSubject(subject);
        
            }
        };
        return preparator;
    }
    private MimeMessagePreparator getMessagePreparator(final String email,final String message,final String subject) {
    	 
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
 
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom(new InternetAddress("mbarsainyan21@gmail.com"));
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(email));
                mimeMessage.setText(message);
                mimeMessage.setSubject(subject);
        
            }
        };
        return preparator;
    }
  
	@Override
	public void sendCompanyRegistrationEmail(CompanyProfile user) {
		String message="Hi, " 
                + " thank you for registering please use the validation code "+ user.getCompany_email();
		MimeMessagePreparator preparator = getMessagePreparator(user.getCompany_email(),message," Validation code");

		try {
			mailSender.send(preparator);
			System.out.println("Message Send...Hurrey");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
}	
	}
	@Override
	public void sendCalendarEmail(UserProfile user,String agenda, Date date1, String time, String location) throws Exception {
		 MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap)MimetypesFileTypeMap.getDefaultFileTypeMap();
		    mimetypes.addMimeTypes("text/calendar ics ICS");
		    MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
		    mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		    
		    
		String message="Hi, " 
                + " thank you for registering please use the validation code "+ user.getEmail();
			 Multipart multipart = new MimeMultipart("alternative");
		 BodyPart messageBodyPart = buildHtmlTextPart();
		    multipart.addBodyPart(messageBodyPart);
		    BodyPart calendarPart = buildCalendarPart(date1, Integer.parseInt(time), agenda, location, user.getFirstname()+" "+ user.getLastname());
		    multipart.addBodyPart(calendarPart);
		    MimeMessagePreparator preparator = getMessagePreparator2(user.getEmail(),message,"Interview Sheduled",multipart);
			
		    
		try {
			mailSender.send(preparator);
			System.out.println("Message Send...Hurrey");
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
}	
	}

	private BodyPart buildHtmlTextPart() throws MessagingException {
	 
    MimeBodyPart descriptionPart = new MimeBodyPart();

    //Note: even if the content is spcified as being text/html, outlook won't read correctly tables at all
    // and only some properties from div:s. Thus, try to avoid too fancy content
    String content = "<font size=\"2\">Please respond to the invitation by visiting our website</font>";
    descriptionPart.setContent(content, "text/html; charset=utf-8");

    return descriptionPart;
}

//define somewhere the icalendar date format
private static SimpleDateFormat iCalendarDateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmm'00'");

private BodyPart buildCalendarPart(Date date,int time,String agenda,String location, String user) throws Exception {

    BodyPart calendarPart = new MimeBodyPart();

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
  
    Date start = cal.getTime();
    cal.add(Calendar.HOUR_OF_DAY, time);
    Date end = cal.getTime();

    //check the icalendar spec in order to build a more complicated meeting request
    String calendarContent =
            "BEGIN:VCALENDAR\n" +
                    "METHOD:REQUEST\n" +
                    "PRODID: BCP - Meeting\n" +
                    "VERSION:2.0\n" +
                    "BEGIN:VEVENT\n" +
                    "DTSTAMP:" + iCalendarDateFormat.format(start) + "\n" +
                    "DTSTART:" + iCalendarDateFormat.format(start)+ "\n" +
                    "DTEND:"  + iCalendarDateFormat.format(end)+ "\n" +
                    "SUMMARY:"+agenda+"\n" +
                    "UID:324\n" +
                    "ATTENDEE;ROLE=REQ-PARTICIPANT;PARTSTAT=NEEDS-ACTION;RSVP=TRUE:MAILTO:organizer@yahoo.com\n" +
                    "ORGANIZER:MAILTO:"+user+"\n" +
                    "LOCATION:"+location+"\n" +
                    "DESCRIPTION:learn some stuff\n" +
                    "SEQUENCE:0\n" +
                    "PRIORITY:5\n" +
                    "CLASS:PUBLIC\n" +
                    "STATUS:CONFIRMED\n" +
                    "TRANSP:OPAQUE\n" +
                    "BEGIN:VALARM\n" +
                    "ACTION:DISPLAY\n" +
                    "DESCRIPTION:REMINDER\n" +
                    "TRIGGER;RELATED=START:-PT00H15M00S\n" +
                    "END:VALARM\n" +
                    "END:VEVENT\n" +
                    "END:VCALENDAR";

    calendarPart.addHeader("Content-Class", "urn:content-classes:calendarmessage");
    calendarPart.setContent(calendarContent, "text/calendar;method=CANCEL");

    return calendarPart;
}
}
