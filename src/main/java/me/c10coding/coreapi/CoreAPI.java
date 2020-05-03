//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi;

import com.google.inject.Injector;
import javax.inject.Inject;
import me.c10coding.coreapi.binder.Binder;
import me.c10coding.coreapi.chat.Chat;

public class CoreAPI {
    @Inject
    private Chat chatFactory;

    public CoreAPI() {
        Binder binder = new Binder(this);
        Injector injector = binder.createInjector();
        injector.injectMembers(this);
    }

    public Chat getChatFactory() {
        return this.chatFactory;
    }
}
