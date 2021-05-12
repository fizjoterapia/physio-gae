package uk.fizjoterapia.gae;

public interface AllMySecrets {

    static AllMySecrets getInstance() {
        return AllMySecretsImpl.INSTANCE;
    }

    String sendGrid();

}
