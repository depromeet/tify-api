# TIFY-api
TIFY(This is for you) API

## POST
- GET /posts : 모든 페이지 목록
- GET /posts/{postId} : 특정 페이지 조회
- POST /posts : 페이지 생성
- GET /posts/{postId}/tags : 페이지의 태그 조회
- POST /posts/{postId}/tags : 페이지에 태그 추가

## RECEIVER
- GET /receivers/{receiverId} : 특정 생일자 조회

## TAG
- GET /tags : 모든 태그 목록
- POST /tags : 태그 생성

## PRESENT
- GET /presents?tag={tagId} : 모든 선물 목록 (태그로 필터)
- POST /presents : 선물 생성

## RECOMMENDATION
- POST /recommendations : 태그-선물 추천관계 생성
