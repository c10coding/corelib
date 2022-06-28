//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.dohaw.corelib;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Project description...
 *
 * @author <a href="nthbyte.com">nthByte LLC</a> [Developer - your name]
 * @version 1.0.0
 */
public class CoreLib {

    public static double tps = 0;

    private static JavaPlugin instance;

    public static JavaPlugin getInstance() {
        checkForNoHookException();
        return instance;
    }

    public static void setInstance(JavaPlugin plugin) {
        instance = plugin;
        if(tps == 0){
            startTPSChecker();
        }
    }

    private static void startTPSChecker(){

        instance.getServer().getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {

            long sec;
            long currentSec;
            int ticks;
            int delay;

            @Override
            public void run() {

                sec = (System.currentTimeMillis() / 1000);
                if (currentSec == sec) {// this code block triggers each tick
                    ticks++;
                } else {// this code block triggers each second

                    currentSec = sec;
                    tps = (tps == 0 ? ticks : ((tps + ticks) / 2));

                    if(tps > 20){
                        tps = 20;
                    }

                    ticks = 0;

                    if ((++delay % 300) == 0) {// this code block triggers each 5 minutes
                        delay = 0;
                    }
                }

            }

        }, 0, 1); // do not change the "1" value, the other one is just initial delay, I recommend 0 = start instantly.

    }

    private static void checkForNoHookException(){
        if(instance == null){
            try {
                throw new NoHookException();
            } catch (NoHookException e) {
                e.printStackTrace();
            }
        }
    }

}
