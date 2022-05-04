package github.resources.img.application.service;

public interface GuestService {

    boolean checkUpload(String ip);

    void updateGuestUpload(String ip);

}
