package pl.awkward.shared;

public enum Authority {
    READ_SHOW("READ_SHOW"),
    ADD_PHOTO("ADD_PHOTO"),
    CHAT("CHAT"),
    CHANGE_ROLE("CHANGE_ROLE"),
    READ_ALL("READ_ALL");


    Authority(String authority) {
        this.authority = authority;
    }

    private final String authority;

    public String getAuthority() {
        return authority;
    }
}
