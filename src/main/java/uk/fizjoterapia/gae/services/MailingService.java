package uk.fizjoterapia.gae.services;

public interface MailingService {

    static MailingService getInstance() {
        return MailingServiceImpl.INSTANCE;
    }

    /**
     * Sends test e-mail
     */
    String test();

}
