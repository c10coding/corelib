# CoreAPI
An API that can assist you in whatever spigot plugin you are developing

## How to use it
Once you download it, you just drop it right into your server's plugin folder. There are a couple ways you can use CoreAPI:

### Hook
```
public final class CustomAnvil extends APIHook
```
If you extend your main class with APIHook or [**BetterJavaPlugin**](https://github.com/c10coding/CoreAPI/blob/master/src/main/java/me/c10coding/coreapi/BetterJavaPlugin.java) (The better option in my opinion), then you can use the next code snippet in your #onEnable() method: 
```
@Override
public void onEnable() {
    hookAPI(this);
}
```
and... that's it! You can now use #getApi() within your plugin and then use the api's methods from there

### Without a hook
If you refuse to use extend your main class with **APIHook** or **BetterJavaPlugin** then you can always hook into the API the old fashion way:
```
public final class Example extends JavaPlugin {

    private CoreAPI api;

    @Override
    public void onEnable() {

        if(getServer().getPluginManager().getPlugin("CoreAPI") != null){
            if(getServer().getPluginManager().getPlugin("CoreAPI").isEnabled()){
                this.api = (CoreAPI) getServer().getPluginManager().getPlugin("CoreAPI");
            }
        }

    }
    
}
```
