//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package me.c10coding.coreapi.binder;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Binder extends AbstractModule {

    private final Object plugin;

    public Binder(Object plugin) {
        this.plugin = plugin;
    }

    public Injector createInjector() {
        return Guice.createInjector(this);
    }

    protected void configure() {
    }

    protected void configure(Class c) {
        this.bind(c).toInstance(this.plugin);
    }
}
