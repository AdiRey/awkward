package pl.awkward.shared;

public enum ApiList {

    PAIR_API("/api/pairs");

    ApiList(String path) {
        this.path = path;
    }

    private final String path;

    public String getPath() {
        return this.path;
    }
}
