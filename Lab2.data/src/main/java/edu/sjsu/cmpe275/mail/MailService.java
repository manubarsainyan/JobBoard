package edu.sjsu.cmpe275.mail;

import java.util.Date;

import edu.sjsu.cmpe275.companyPositions.CompanyPosition;
import edu.sjsu.cmpe275.companyprofile.CompanyProfile;
import edu.sjsu.cmpe275.user.UserProfile;

public interface MailService {
 
    public void sendRegistrationEmail(final UserProfile user);
    public void sendCompanyRegistrationEmail(final CompanyProfile user);
    public void sendApplicationEmail(final CompanyPosition position,final UserProfile user);
    public void sendPositionEmail(final CompanyPosition position,final UserProfile user);
	public void sendUnmarkOnCloseEmail(CompanyPosition position, UserProfile user);
	public void sendRejectionEmail(CompanyPosition position, UserProfile user);
	public void sendAcceptanceEmail(CompanyPosition position, UserProfile user);
	void sendCalendarEmail(UserProfile user, String agenda, Date date1, String time, String location) throws Exception;
}
