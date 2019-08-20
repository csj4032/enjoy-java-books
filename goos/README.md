# 테스트 주도 개발로 배우는 객체지향 설계와 실천

## Growing Object-Oriented Software, Guided by Tests
* http://www.growing-object-oriented-software.com/
* https://github.com/sf105/goos-code

## Creating a Container

```bash
docker pull sameersbn/openfire

docker build -t sameersbn/openfire github.com/sameersbn/docker-openfire

docker run --name openfire -d --restart=always --publish 9090:9090 --publish 5222:5222 --publish 7777:7777 --volume /var/lib/openfire sameersbn/openfire:3.10.3-19
```