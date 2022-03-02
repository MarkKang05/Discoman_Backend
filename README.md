# 💿 [Discoman](https://github.com/MarkKang05/Discoman_Backend) (개발 중⭐️)


# 1. 🏬 서비스 소개
* Discogs 서비스에서 영감을 받아 제작하게 되었습니다.
* LP(vinyl)앨범과 여러 파생 릴리즈를 등록하여 중고 거래 할 수 있는 서비스입니다.
* [Demo220223](https://youtu.be/SdhS7QTI-Nc)
---


# 2. 📚 기술 스택
* Spring Boot + OpenJDK 11
* Spring Data JPA
* Spring Security
* Redis
* MySQL
* Docker
* Linux

([Frontend](https://github.com/MarkKang05/Discoman_Frontend))


---


# 3. 💻 서비스 구현
* 마스터 앨범과 릴리스 앨범
    * 한 앨범(마스터 앨범)에서 파생된 여러 버전의 앨범(릴리즈 앨범)을 구조로 구현하였습니다.
 
* 웹, 모바일 로그인/권한
    * JWT토큰과 쿠키를 이용하여 로그인 
    	* [JwtFilter](https://github.com/MarkKang05/Discoman_Backend/blob/master/src/main/java/com/mywork/discoman/global/config/jwt/JwtAuthFilter.java)
    	* [JwtProvider](https://github.com/MarkKang05/Discoman_Backend/blob/master/src/main/java/com/mywork/discoman/global/config/jwt/JwtProvider.java)
    * 스프링 시큐리티와 함께 권한관리(TODO)

* JPA를 이용한 DB모델링

<img src="https://user-images.githubusercontent.com/47387289/155132624-651d9688-c44b-44c0-9d7f-617e6ecd1541.png" width="600"/>

---

# 4. 🛠 배포 준비

* 라즈베리파이 서버에 도커를 이용하여 운영
    * [Dokerfile](https://github.com/MarkKang05/Discoman_Backend/blob/master/Dockerfile)
    * [run.sh](https://github.com/MarkKang05/Discoman_Backend/blob/master/run.sh)

---


# 5. 📬 구현된 API

* Artists
	* GET /artists : 모든 아티스트 조회
	* GET /artists/{artist_id} : 아티스트 조회
	* POST /artists/ : 아티스트 생성
	* PUT /artists/{artist_id} : 아티스트 수정
	* DELETE /artists/{artist_id} : 아티스트 삭제
	* POST /artists/image : 아티스트 이미지 추가

* Labels
	* GET /labels : 모든 라벨 조회
	* GET /labels/{label_id} : 라벨 조회
	* POST /labels/ : 라벨 생성
	* PUT /labels/{label_id} : 라벨 수정
	* DELETE /labels/{label_id} : 라벨 삭제

* MasterAlbums
	* GET /masterAlbums : 모든 마스터 앨범 조회
	* GET /masterAlbums/{masterAlbum_id} : 마스터 앨범 조회
	* POST /masterAlbums/ : 마스터 앨범 생성
	* PUT /masterAlbums/{masterAlbum_id} : 마스터 앨범 수정
	* DELETE /masterAlbums/{masterAlbum_id} : 마스터 앨범 삭제
	* POST /masterAlbums/image : 마스터 앨범 이미지 추가
	
* ReleaseAlbums
	* GET /releaseAlbums : 모든 릴리즈 앨범 조회
	* GET /releaseAlbums/{releaseAlbum_id} : 릴리즈 앨범 조회
	* POST /releaseAlbums/ : 릴리즈 앨범 생성
	* PUT /releaseAlbums/{releaseAlbum_id} : 릴리즈 앨범 수정
	* DELETE /releaseAlbums/{releaseAlbum_id} : 릴리즈 앨범 삭제
