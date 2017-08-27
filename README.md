# WAS 구현

자바로 간단한 WAS(Web Application Server)를 만들기

```
* maven target을 수정하지 않기 위해 was.jar (symbolic link) 사용
> (ln -s ./target/was.jar was.jar)
> mvn clean package
> java –jar was.jar
```

1. HTTP/1.1 의 Host 헤더를 해석하세요.

```
1) local에 Host 등록
 > 127.0.0.1 www.server1.com
 > 127.0.0.1 www.server2.com
2) port 8000 으로 호출
 > http://www.server1.com:8000
 > http://www.server1.com:8000
```

2. 설정 파일로 관리하세요.
```
1) pom.xml env 설정으로 개발환경 분리
config path: ./src/main/resources/dev/application.json
{
  "default": "was",
  "port": 8000,
  "blocked_extension": "exe,bat",
  "servers": [
    {
      "server_name": "was",
      "domain": "www.server1.com",
      "http_root": "/src/main/webapp1/WEB-INF",
      "source": {
        "index": "index.html",
        "403": "/403.html",
        "404": "/404.html",
        "500": "/500.html"
      }
    },
    {
      "server_name": "simple",
      "domain": "www.server2.com",
      "http_root": "/src/main/webapp2/WEB-INF",
      "source": {
        "index": "index.html",
        "403": "/403.html",
        "404": "/404.html",
        "500": "/500.html"
      }
    }
  ]
}
```

3. 403, 404, 500 에러를 처리
```
Exception 과 file read 여부를 파악하여 처리
```

4. 보안 규칙
```
config.blocked_extension을 설정하여 예외처리
상위 폴더 접근자 예외처리
```

5. logback 프레임워크
```
config path:  ./src/main/resources/dev/logback.xml
log folder ./logs/
일별 StackTrace 남김
```

6. SimpleServlet 구현
```
package: com.simple
router config:  ./src/main/resources/dev/mapping.json
1) package 별 라우터 설정할수 있는 구조 설계
2) config load시 라우터 등록
3) mapping
 > http://localhost:8000/Hello
 > http://localhost:8000/service.Hello
4) servlet 구현
 > http://localhost:8000/Hello?name=eden
 > http://localhost:8000/service.Hello?name=eden
```

7. 현재 시각을 출력
```
package: com.simple
 > http://localhost:8000/time
```
8. JUnit4 를 이용한 테스트 케이스 : 미구현

9. TODO List
```
* mime type
* post 구현
* rest api router 구현
* 소스 정리 및 리펙토링
* prod shell script 작성
* web root 구조 설계 및 프론트 프레임워크 도입
* 도메인별 라우터 분리
....
```