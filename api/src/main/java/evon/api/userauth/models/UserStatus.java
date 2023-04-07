package evon.api.userauth.models;

public enum UserStatus {
    ACTIVE(1), INACTIVE(2);

    public int value;
    UserStatus(int value){
        this.value=value;
    }
}
