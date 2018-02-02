package bank;

public class Account {
    private String id;
    private Instrument instrument;

    public Account(String id, Instrument instrument) {
        this.id = id;
        this.instrument = instrument;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
}
