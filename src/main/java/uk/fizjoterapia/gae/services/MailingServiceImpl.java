package uk.fizjoterapia.gae.services;

import com.sendgrid.*;
import uk.fizjoterapia.gae.AllMySecrets;

import java.io.IOException;

public class MailingServiceImpl implements MailingService {

    final static MailingService INSTANCE = new MailingServiceImpl();
    private final AllMySecrets allMySecrets = AllMySecrets.getInstance();

    @Override
    public String test() {
        Email from = new Email("joanna@fizjoterapia.uk");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("bart@prokop.dev");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(allMySecrets.sendGrid());
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
//            System.out.println(response.getStatusCode());
//            System.out.println(response.getBody());
//            System.out.println(response.getHeaders());
            return response.getBody();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

}
