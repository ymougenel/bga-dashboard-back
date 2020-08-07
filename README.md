# BGA Dashboard back

Backend dashboard for [the bga project](https://github.com/ymougenel/bga-rankings-dashboard)

## Development

1. Run DB `docker-compose up -d bga-ranking-database`
2. Compile app `mvn package -DskipTests`
3. Run app `java -jar -Dspring.profiles.active=container target/bga-ranking-X.X.X.jar` (where XXX is the version)
4. init the games `http://localhost:8080/game/all`.
4. init the rankings `http://localhost:8080/player/save/1127`.


## License
The project is under [Mozilla Public License (v2.0)](./License).
