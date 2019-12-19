# 테스트 주도 개발로 배우는 객체지향 설계와 실천

## Growing Object-Oriented Software, Guided by Tests
* http://www.growing-object-oriented-software.com/
* https://github.com/sf105/goos-code

## Openfire(XMPP) Creating a Container

```bash
docker pull gizmotronic/openfire:4.4.4

docker build -t gizmotronic/openfire github.com/gizmotronic/docker-openfire

docker run --name openfire -d --restart=always --publish 9090:9090 --publish 5222:5222 --publish 7777:7777 --volume /tmp/docker/openfire:/var/lib/openfire gizmotronic/openfire:4.4.4
```

## Openfire
### Create New User 
* auction-item-65432 / auction
* auction-item-54321 / auction
* sniper / sniper