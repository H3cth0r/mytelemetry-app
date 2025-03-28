


### Clean Caches Before Building
```
./gradlew clean
rm -rf ~/.gradle/caches/transforms-*
```

### Rebuild
```
./gradlew assembleDebug --stacktrace
```


### Output at
```
./gradlew clean assembleDebug
```

### Tree without gitignore files
```
tree -I "$(grep -vE '^#|^!' .gitignore | sed '/^$/d' | tr '\n' '|' | sed 's/|$//')"
```
