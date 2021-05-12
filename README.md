# CoreLib
An libary that can assist you in whatever spigot plugin you are developing

## How to use it
### Maven
If you use maven, you can simply just drop these dependency and repository blocks in your pom.xml:

```
<repository>
  <id>myMavenRepoRead</id>
  <url>https://mymavenrepo.com/repo/NS8ChmY70jB4I67iQ6bU/</url>
</repository>
```
```
<dependency>
  <groupId>net.dohaw</groupId>
  <artifactId>CoreLib</artifactId>
  <version>3.1.3</version>
</dependency>
```
### Hook
Once you've done that, make sure you type this in your #onEnable method of your plugin's main class:
```
CoreLib.setInstance(this);
```

And, you're done! You may now use all of CoreLib's classes (To see what they are, look at the source code. I will edit the wiki sometime soon to help you out)
