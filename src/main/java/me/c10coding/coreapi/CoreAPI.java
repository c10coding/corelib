//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi;

import com.google.inject.Injector;
import javax.inject.Inject;

import me.c10coding.coreapi.binder.Binder;
import me.c10coding.coreapi.chat.Chat;
import me.c10coding.coreapi.files.ConfigManager;
import me.c10coding.coreapi.numbers.MathHelper;
import me.c10coding.coreapi.serializers.LocationSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CoreAPI {

    @Inject private Chat chatFactory;
    @Inject private MathHelper mathHelper;

    public CoreAPI() {
        Binder binder = new Binder(this);
        Injector injector = binder.createInjector();
        injector.injectMembers(this);
    }

    public Chat getChatFactory() {
        return this.chatFactory;
    }

    public LocationSerializer getLocationSerializer(FileConfiguration config){
        return new LocationSerializer(config);
    }

    public MathHelper getMathHelper(){
        return this.mathHelper;
    }

    public Binder getBinder(Object b){
        return new Binder(b);
    }

}
