package maow.testmod;

import maow.modloader.api.Entrypoint;

public class Main implements Entrypoint {
    @Override
    public void onInit() {
        System.out.println("Invoked onInit properly.");
    }
}
