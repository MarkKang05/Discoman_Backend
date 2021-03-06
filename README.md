# ๐ฟ [Discoman](https://github.com/MarkKang05/Discoman_Backend) (๊ฐ๋ฐ ์คโญ๏ธ)


# 1. ๐ฌ ์๋น์ค ์๊ฐ
* Discogs ์๋น์ค์์ ์๊ฐ์ ๋ฐ์ ์ ์ํ๊ฒ ๋์์ต๋๋ค.
* LP(vinyl)์จ๋ฒ๊ณผ ์ฌ๋ฌ ํ์ ๋ฆด๋ฆฌ์ฆ๋ฅผ ๋ฑ๋กํ์ฌ ์ค๊ณ  ๊ฑฐ๋ ํ  ์ ์๋ ์๋น์ค์๋๋ค.
* [Demo220223](https://youtu.be/SdhS7QTI-Nc)
---


# 2. ๐ ๊ธฐ์  ์คํ
* Spring Boot + OpenJDK 11
* Spring Data JPA
* Spring Security
* Redis
* MySQL
* Amazon S3
* Docker
* Linux

([Frontend](https://github.com/MarkKang05/Discoman_Frontend))


---


# 3. ๐ป ์๋น์ค ๊ตฌํ
* ๋ง์คํฐ ์จ๋ฒ๊ณผ ๋ฆด๋ฆฌ์ค ์จ๋ฒ
    * ํ ์จ๋ฒ(๋ง์คํฐ ์จ๋ฒ)์์ ํ์๋ ์ฌ๋ฌ ๋ฒ์ ์ ์จ๋ฒ(๋ฆด๋ฆฌ์ฆ ์จ๋ฒ)์ ๊ตฌ์กฐ๋ก ๊ตฌํํ์์ต๋๋ค.
 
* ์น, ๋ชจ๋ฐ์ผ ๋ก๊ทธ์ธ/๊ถํ
    * JWTํ ํฐ๊ณผ ์ฟ ํค๋ฅผ ์ด์ฉํ์ฌ ๋ก๊ทธ์ธ 
    	* [JwtFilter](https://github.com/MarkKang05/Discoman_Backend/blob/master/src/main/java/com/mywork/discoman/global/config/jwt/JwtAuthFilter.java)
    	* [JwtProvider](https://github.com/MarkKang05/Discoman_Backend/blob/master/src/main/java/com/mywork/discoman/global/config/jwt/JwtProvider.java)
    * ์คํ๋ง ์ํ๋ฆฌํฐ์ ํจ๊ป ๊ถํ๊ด๋ฆฌ(TODO)

* JPA๋ฅผ ์ด์ฉํ DB๋ชจ๋ธ๋ง

<img src="https://user-images.githubusercontent.com/47387289/155132624-651d9688-c44b-44c0-9d7f-617e6ecd1541.png" width="600"/>

---

# 4. ๐  ๋ฐฐํฌ ์ค๋น

* ๋ผ์ฆ๋ฒ ๋ฆฌํ์ด ์๋ฒ์ ๋์ปค๋ฅผ ์ด์ฉํ์ฌ ์ด์
    * [Dokerfile](https://github.com/MarkKang05/Discoman_Backend/blob/master/Dockerfile)
    * [run.sh](https://github.com/MarkKang05/Discoman_Backend/blob/master/run.sh)

---


# 5. ๐ฌ ๊ตฌํ๋ API

* Artists
	* GET /artists : ๋ชจ๋  ์ํฐ์คํธ ์กฐํ
	* GET /artists/{artist_id} : ์ํฐ์คํธ ์กฐํ
	* POST /artists/ : ์ํฐ์คํธ ์์ฑ
	* PUT /artists/{artist_id} : ์ํฐ์คํธ ์์ 
	* DELETE /artists/{artist_id} : ์ํฐ์คํธ ์ญ์ 
	* POST /artists/image : ์ํฐ์คํธ ์ด๋ฏธ์ง ์ถ๊ฐ

* Labels
	* GET /labels : ๋ชจ๋  ๋ผ๋ฒจ ์กฐํ
	* GET /labels/{label_id} : ๋ผ๋ฒจ ์กฐํ
	* POST /labels/ : ๋ผ๋ฒจ ์์ฑ
	* PUT /labels/{label_id} : ๋ผ๋ฒจ ์์ 
	* DELETE /labels/{label_id} : ๋ผ๋ฒจ ์ญ์ 

* MasterAlbums
	* GET /masterAlbums : ๋ชจ๋  ๋ง์คํฐ ์จ๋ฒ ์กฐํ
	* GET /masterAlbums/{masterAlbum_id} : ๋ง์คํฐ ์จ๋ฒ ์กฐํ
	* POST /masterAlbums/ : ๋ง์คํฐ ์จ๋ฒ ์์ฑ
	* PUT /masterAlbums/{masterAlbum_id} : ๋ง์คํฐ ์จ๋ฒ ์์ 
	* DELETE /masterAlbums/{masterAlbum_id} : ๋ง์คํฐ ์จ๋ฒ ์ญ์ 
	* POST /masterAlbums/image : ๋ง์คํฐ ์จ๋ฒ ์ด๋ฏธ์ง ์ถ๊ฐ
	
* ReleaseAlbums
	* GET /releaseAlbums : ๋ชจ๋  ๋ฆด๋ฆฌ์ฆ ์จ๋ฒ ์กฐํ
	* GET /releaseAlbums/{releaseAlbum_id} : ๋ฆด๋ฆฌ์ฆ ์จ๋ฒ ์กฐํ
	* POST /releaseAlbums/ : ๋ฆด๋ฆฌ์ฆ ์จ๋ฒ ์์ฑ
	* PUT /releaseAlbums/{releaseAlbum_id} : ๋ฆด๋ฆฌ์ฆ ์จ๋ฒ ์์ 
	* DELETE /releaseAlbums/{releaseAlbum_id} : ๋ฆด๋ฆฌ์ฆ ์จ๋ฒ ์ญ์ 

# 5. ๐ TODO
- [ ] OAuth2 ๊ตฌํ
- [ ] ์จ๋ฒ ๋ฐ์ดํฐ ์ก์๋ก ๋๋ ์๋ก๋ ์ฒ๋ฆฌ(Admin)
- [ ] ์ ์  ์ด๋ฏธ์ง
- [ ] ํ์๊ฐ์ ์ ์ธ์ฆ ์ปจํธ๋กค๋ฌ ์ถ๊ฐ(์์ด๋, ์ด๋ฉ์ผ ์ค๋ณต)
