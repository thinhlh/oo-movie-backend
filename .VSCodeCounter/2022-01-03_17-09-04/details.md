# Details

Date : 2022-01-03 17:09:04

Directory /Users/hoangthinh/Data/Learning/Object Oriented Design/oo-movie-backend/src/main/kotlin/com/housing/movie

Total : 105 files,  2236 codes, 130 comments, 630 blanks, all 2996 lines

[summary](results.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [src/main/kotlin/com/housing/movie/HousingMovieApplication.kt](/src/main/kotlin/com/housing/movie/HousingMovieApplication.kt) | Kotlin | 9 | 0 | 5 | 14 |
| [src/main/kotlin/com/housing/movie/base/BaseController.kt](/src/main/kotlin/com/housing/movie/base/BaseController.kt) | Kotlin | 11 | 0 | 4 | 15 |
| [src/main/kotlin/com/housing/movie/base/BaseException.kt](/src/main/kotlin/com/housing/movie/base/BaseException.kt) | Kotlin | 3 | 0 | 1 | 4 |
| [src/main/kotlin/com/housing/movie/base/BaseResponse.kt](/src/main/kotlin/com/housing/movie/base/BaseResponse.kt) | Kotlin | 15 | 0 | 6 | 21 |
| [src/main/kotlin/com/housing/movie/base/BaseSerializer.kt](/src/main/kotlin/com/housing/movie/base/BaseSerializer.kt) | Kotlin | 5 | 0 | 2 | 7 |
| [src/main/kotlin/com/housing/movie/base/BaseUseCase.kt](/src/main/kotlin/com/housing/movie/base/BaseUseCase.kt) | Kotlin | 4 | 0 | 1 | 5 |
| [src/main/kotlin/com/housing/movie/base/CustomResponseEntityExceptionHandler.kt](/src/main/kotlin/com/housing/movie/base/CustomResponseEntityExceptionHandler.kt) | Kotlin | 124 | 16 | 34 | 174 |
| [src/main/kotlin/com/housing/movie/config/AmazonConfig.kt](/src/main/kotlin/com/housing/movie/config/AmazonConfig.kt) | Kotlin | 27 | 0 | 5 | 32 |
| [src/main/kotlin/com/housing/movie/config/FirebaseConfig.kt](/src/main/kotlin/com/housing/movie/config/FirebaseConfig.kt) | Kotlin | 31 | 0 | 6 | 37 |
| [src/main/kotlin/com/housing/movie/config/JsonConfiguration.kt](/src/main/kotlin/com/housing/movie/config/JsonConfiguration.kt) | Kotlin | 16 | 0 | 3 | 19 |
| [src/main/kotlin/com/housing/movie/config/MovieConfiguration.kt](/src/main/kotlin/com/housing/movie/config/MovieConfiguration.kt) | Kotlin | 15 | 0 | 4 | 19 |
| [src/main/kotlin/com/housing/movie/config/WebConfig.kt](/src/main/kotlin/com/housing/movie/config/WebConfig.kt) | Kotlin | 10 | 11 | 2 | 23 |
| [src/main/kotlin/com/housing/movie/exceptions/ConversionException.kt](/src/main/kotlin/com/housing/movie/exceptions/ConversionException.kt) | Kotlin | 4 | 0 | 3 | 7 |
| [src/main/kotlin/com/housing/movie/exceptions/MissingPropertyException.kt](/src/main/kotlin/com/housing/movie/exceptions/MissingPropertyException.kt) | Kotlin | 4 | 0 | 2 | 6 |
| [src/main/kotlin/com/housing/movie/exceptions/NotFoundException.kt](/src/main/kotlin/com/housing/movie/exceptions/NotFoundException.kt) | Kotlin | 4 | 0 | 3 | 7 |
| [src/main/kotlin/com/housing/movie/exceptions/ObjectAlreadyExistsException.kt](/src/main/kotlin/com/housing/movie/exceptions/ObjectAlreadyExistsException.kt) | Kotlin | 4 | 0 | 3 | 7 |
| [src/main/kotlin/com/housing/movie/exceptions/ObjectDisabledException.kt](/src/main/kotlin/com/housing/movie/exceptions/ObjectDisabledException.kt) | Kotlin | 4 | 0 | 3 | 7 |
| [src/main/kotlin/com/housing/movie/exceptions/ResourceHandlingException.kt](/src/main/kotlin/com/housing/movie/exceptions/ResourceHandlingException.kt) | Kotlin | 4 | 0 | 2 | 6 |
| [src/main/kotlin/com/housing/movie/exceptions/RunOutOfUsageException.kt](/src/main/kotlin/com/housing/movie/exceptions/RunOutOfUsageException.kt) | Kotlin | 4 | 0 | 2 | 6 |
| [src/main/kotlin/com/housing/movie/features/common/controller/CommonController.kt](/src/main/kotlin/com/housing/movie/features/common/controller/CommonController.kt) | Kotlin | 22 | 0 | 5 | 27 |
| [src/main/kotlin/com/housing/movie/features/common/entity/movie_person/MoviePerson.kt](/src/main/kotlin/com/housing/movie/features/common/entity/movie_person/MoviePerson.kt) | Kotlin | 22 | 0 | 5 | 27 |
| [src/main/kotlin/com/housing/movie/features/common/entity/movie_person/MoviePersonKey.kt](/src/main/kotlin/com/housing/movie/features/common/entity/movie_person/MoviePersonKey.kt) | Kotlin | 11 | 0 | 5 | 16 |
| [src/main/kotlin/com/housing/movie/features/common/entity/movie_person/PersonType.kt](/src/main/kotlin/com/housing/movie/features/common/entity/movie_person/PersonType.kt) | Kotlin | 5 | 0 | 1 | 6 |
| [src/main/kotlin/com/housing/movie/features/common/serializers/MovieSerializer.kt](/src/main/kotlin/com/housing/movie/features/common/serializers/MovieSerializer.kt) | Kotlin | 55 | 8 | 15 | 78 |
| [src/main/kotlin/com/housing/movie/features/company/controller/CompanyController.kt](/src/main/kotlin/com/housing/movie/features/company/controller/CompanyController.kt) | Kotlin | 6 | 0 | 3 | 9 |
| [src/main/kotlin/com/housing/movie/features/company/data/CompanyRepository.kt](/src/main/kotlin/com/housing/movie/features/company/data/CompanyRepository.kt) | Kotlin | 6 | 0 | 2 | 8 |
| [src/main/kotlin/com/housing/movie/features/company/domain/Company.kt](/src/main/kotlin/com/housing/movie/features/company/domain/Company.kt) | Kotlin | 19 | 3 | 12 | 34 |
| [src/main/kotlin/com/housing/movie/features/discount/controller/DiscountController.kt](/src/main/kotlin/com/housing/movie/features/discount/controller/DiscountController.kt) | Kotlin | 58 | 0 | 10 | 68 |
| [src/main/kotlin/com/housing/movie/features/discount/data/repository/DiscountRepository.kt](/src/main/kotlin/com/housing/movie/features/discount/data/repository/DiscountRepository.kt) | Kotlin | 7 | 0 | 5 | 12 |
| [src/main/kotlin/com/housing/movie/features/discount/data/service/DiscountServiceImpl.kt](/src/main/kotlin/com/housing/movie/features/discount/data/service/DiscountServiceImpl.kt) | Kotlin | 69 | 0 | 23 | 92 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/entity/Discount.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/entity/Discount.kt) | Kotlin | 23 | 0 | 10 | 33 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/service/DiscountService.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/service/DiscountService.kt) | Kotlin | 17 | 0 | 9 | 26 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/DeleteDiscountUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/DeleteDiscountUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/EnableDiscountUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/EnableDiscountUseCase.kt) | Kotlin | 14 | 0 | 2 | 16 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/GetAllDiscountsUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/GetAllDiscountsUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/GetDiscountByCodeUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/GetDiscountByCodeUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/GetDiscountByIdUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/GetDiscountByIdUseCase.kt) | Kotlin | 14 | 0 | 2 | 16 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/UseDiscountUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/UseDiscountUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/create_discount/CreateDiscountRequest.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/create_discount/CreateDiscountRequest.kt) | Kotlin | 34 | 0 | 8 | 42 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/create_discount/CreateDiscountUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/create_discount/CreateDiscountUseCase.kt) | Kotlin | 13 | 0 | 3 | 16 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/update_discount/UpdateDiscountRequest.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/update_discount/UpdateDiscountRequest.kt) | Kotlin | 33 | 0 | 10 | 43 |
| [src/main/kotlin/com/housing/movie/features/discount/domain/usecase/update_discount/UpdateDiscountUseCase.kt](/src/main/kotlin/com/housing/movie/features/discount/domain/usecase/update_discount/UpdateDiscountUseCase.kt) | Kotlin | 14 | 0 | 2 | 16 |
| [src/main/kotlin/com/housing/movie/features/episode/controller/EpisodeController.kt](/src/main/kotlin/com/housing/movie/features/episode/controller/EpisodeController.kt) | Kotlin | 51 | 0 | 8 | 59 |
| [src/main/kotlin/com/housing/movie/features/episode/data/repository/EpisodeRepository.kt](/src/main/kotlin/com/housing/movie/features/episode/data/repository/EpisodeRepository.kt) | Kotlin | 11 | 0 | 5 | 16 |
| [src/main/kotlin/com/housing/movie/features/episode/data/service/EpisodeServiceImpl.kt](/src/main/kotlin/com/housing/movie/features/episode/data/service/EpisodeServiceImpl.kt) | Kotlin | 65 | 2 | 29 | 96 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/entity/Episode.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/entity/Episode.kt) | Kotlin | 22 | 0 | 5 | 27 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/service/EpisodeService.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/service/EpisodeService.kt) | Kotlin | 12 | 0 | 6 | 18 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/usecase/DeleteEpisodeUseCase.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/usecase/DeleteEpisodeUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/usecase/EnableEpisodeUseCase.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/usecase/EnableEpisodeUseCase.kt) | Kotlin | 14 | 0 | 2 | 16 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/usecase/GetEpisodeUseCase.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/usecase/GetEpisodeUseCase.kt) | Kotlin | 14 | 0 | 4 | 18 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/usecase/create_episode/CreateEpisodeRequest.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/usecase/create_episode/CreateEpisodeRequest.kt) | Kotlin | 17 | 0 | 2 | 19 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/usecase/create_episode/CreateEpisodeUseCase.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/usecase/create_episode/CreateEpisodeUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/usecase/update_episode/UpdateEpisodeRequest.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/usecase/update_episode/UpdateEpisodeRequest.kt) | Kotlin | 18 | 0 | 6 | 24 |
| [src/main/kotlin/com/housing/movie/features/episode/domain/usecase/update_episode/UpdateEpisodeUseCase.kt](/src/main/kotlin/com/housing/movie/features/episode/domain/usecase/update_episode/UpdateEpisodeUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/genre/controller/GenreController.kt](/src/main/kotlin/com/housing/movie/features/genre/controller/GenreController.kt) | Kotlin | 46 | 0 | 7 | 53 |
| [src/main/kotlin/com/housing/movie/features/genre/data/repository/GenreRepository.kt](/src/main/kotlin/com/housing/movie/features/genre/data/repository/GenreRepository.kt) | Kotlin | 12 | 0 | 6 | 18 |
| [src/main/kotlin/com/housing/movie/features/genre/data/service/GenreServiceImpl.kt](/src/main/kotlin/com/housing/movie/features/genre/data/service/GenreServiceImpl.kt) | Kotlin | 62 | 2 | 13 | 77 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/entity/Genre.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/entity/Genre.kt) | Kotlin | 29 | 0 | 11 | 40 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/service/GenreService.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/service/GenreService.kt) | Kotlin | 12 | 0 | 6 | 18 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/usecase/DeleteGenreUseCase.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/usecase/DeleteGenreUseCase.kt) | Kotlin | 11 | 0 | 3 | 14 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/usecase/EnableGenreUseCase.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/usecase/EnableGenreUseCase.kt) | Kotlin | 11 | 0 | 2 | 13 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/usecase/GetAllGenresUseCase.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/usecase/GetAllGenresUseCase.kt) | Kotlin | 15 | 0 | 2 | 17 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/usecase/create_genre/CreateGenreRequest.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/usecase/create_genre/CreateGenreRequest.kt) | Kotlin | 12 | 0 | 4 | 16 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/usecase/create_genre/CreateGenreUseCase.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/usecase/create_genre/CreateGenreUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/usecase/update_genre/UpdateGenreRequest.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/usecase/update_genre/UpdateGenreRequest.kt) | Kotlin | 16 | 0 | 4 | 20 |
| [src/main/kotlin/com/housing/movie/features/genre/domain/usecase/update_genre/UpdateGenreUseCase.kt](/src/main/kotlin/com/housing/movie/features/genre/domain/usecase/update_genre/UpdateGenreUseCase.kt) | Kotlin | 11 | 0 | 2 | 13 |
| [src/main/kotlin/com/housing/movie/features/movie/controller/MovieController.kt](/src/main/kotlin/com/housing/movie/features/movie/controller/MovieController.kt) | Kotlin | 82 | 0 | 11 | 93 |
| [src/main/kotlin/com/housing/movie/features/movie/data/repository/MovieDetailRepository.kt](/src/main/kotlin/com/housing/movie/features/movie/data/repository/MovieDetailRepository.kt) | Kotlin | 6 | 0 | 2 | 8 |
| [src/main/kotlin/com/housing/movie/features/movie/data/repository/MovieRepository.kt](/src/main/kotlin/com/housing/movie/features/movie/data/repository/MovieRepository.kt) | Kotlin | 12 | 0 | 8 | 20 |
| [src/main/kotlin/com/housing/movie/features/movie/data/service/MovieServiceImpl.kt](/src/main/kotlin/com/housing/movie/features/movie/data/service/MovieServiceImpl.kt) | Kotlin | 91 | 1 | 37 | 129 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/entity/Movie.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/entity/Movie.kt) | Kotlin | 50 | 0 | 12 | 62 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/entity/MovieDetail.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/entity/MovieDetail.kt) | Kotlin | 37 | 0 | 21 | 58 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/service/MovieService.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/service/MovieService.kt) | Kotlin | 16 | 0 | 10 | 26 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/DeleteMovieUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/DeleteMovieUseCase.kt) | Kotlin | 13 | 0 | 3 | 16 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/EnableMovieUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/EnableMovieUseCase.kt) | Kotlin | 14 | 0 | 2 | 16 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetAllMoviesUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetAllMoviesUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetMovieByIdUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetMovieByIdUseCase.kt) | Kotlin | 12 | 0 | 2 | 14 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetMovieByTitleUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetMovieByTitleUseCase.kt) | Kotlin | 15 | 0 | 2 | 17 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetMoviesByGenreUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/GetMoviesByGenreUseCase.kt) | Kotlin | 12 | 0 | 2 | 14 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/assign_tags_to_movie/AssignGenresToMovieRequest.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/assign_tags_to_movie/AssignGenresToMovieRequest.kt) | Kotlin | 10 | 0 | 3 | 13 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/assign_tags_to_movie/AssignGenresToMovieUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/assign_tags_to_movie/AssignGenresToMovieUseCase.kt) | Kotlin | 12 | 0 | 2 | 14 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/create_movie/CreateMovieRequest.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/create_movie/CreateMovieRequest.kt) | Kotlin | 60 | 0 | 22 | 82 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/create_movie/CreateMovieUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/create_movie/CreateMovieUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/update_movie/UpdateMovieRequest.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/update_movie/UpdateMovieRequest.kt) | Kotlin | 92 | 0 | 24 | 116 |
| [src/main/kotlin/com/housing/movie/features/movie/domain/usecase/update_movie/UpdateMovieUseCase.kt](/src/main/kotlin/com/housing/movie/features/movie/domain/usecase/update_movie/UpdateMovieUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/person/domain/Person.kt](/src/main/kotlin/com/housing/movie/features/person/domain/Person.kt) | Kotlin | 21 | 4 | 14 | 39 |
| [src/main/kotlin/com/housing/movie/features/plan/controller/PlanController.kt](/src/main/kotlin/com/housing/movie/features/plan/controller/PlanController.kt) | Kotlin | 21 | 0 | 4 | 25 |
| [src/main/kotlin/com/housing/movie/features/plan/data/repository/PlanRepository.kt](/src/main/kotlin/com/housing/movie/features/plan/data/repository/PlanRepository.kt) | Kotlin | 8 | 0 | 2 | 10 |
| [src/main/kotlin/com/housing/movie/features/plan/data/service/PlanServiceImpl.kt](/src/main/kotlin/com/housing/movie/features/plan/data/service/PlanServiceImpl.kt) | Kotlin | 32 | 0 | 10 | 42 |
| [src/main/kotlin/com/housing/movie/features/plan/domain/entity/Plan.kt](/src/main/kotlin/com/housing/movie/features/plan/domain/entity/Plan.kt) | Kotlin | 14 | 0 | 3 | 17 |
| [src/main/kotlin/com/housing/movie/features/plan/domain/service/PlanService.kt](/src/main/kotlin/com/housing/movie/features/plan/domain/service/PlanService.kt) | Kotlin | 8 | 0 | 4 | 12 |
| [src/main/kotlin/com/housing/movie/features/plan/domain/usecase/DeletePlanUseCase.kt](/src/main/kotlin/com/housing/movie/features/plan/domain/usecase/DeletePlanUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/plan/domain/usecase/EnablePlanUseCase.kt](/src/main/kotlin/com/housing/movie/features/plan/domain/usecase/EnablePlanUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/plan/domain/usecase/GetPlansUseCase.kt](/src/main/kotlin/com/housing/movie/features/plan/domain/usecase/GetPlansUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/resource/controller/ResourcesController.kt](/src/main/kotlin/com/housing/movie/features/resource/controller/ResourcesController.kt) | Kotlin | 28 | 0 | 5 | 33 |
| [src/main/kotlin/com/housing/movie/features/resource/data/service/AmazonResourceService.kt](/src/main/kotlin/com/housing/movie/features/resource/data/service/AmazonResourceService.kt) | Kotlin | 24 | 61 | 2 | 87 |
| [src/main/kotlin/com/housing/movie/features/resource/data/service/FirebaseResourceService.kt](/src/main/kotlin/com/housing/movie/features/resource/data/service/FirebaseResourceService.kt) | Kotlin | 40 | 0 | 10 | 50 |
| [src/main/kotlin/com/housing/movie/features/resource/domain/service/ResourcesService.kt](/src/main/kotlin/com/housing/movie/features/resource/domain/service/ResourcesService.kt) | Kotlin | 30 | 0 | 7 | 37 |
| [src/main/kotlin/com/housing/movie/features/resource/domain/usecase/GenerateObjectLinkUseCase.kt](/src/main/kotlin/com/housing/movie/features/resource/domain/usecase/GenerateObjectLinkUseCase.kt) | Kotlin | 14 | 0 | 2 | 16 |
| [src/main/kotlin/com/housing/movie/features/resource/domain/usecase/UploadVideoUseCase.kt](/src/main/kotlin/com/housing/movie/features/resource/domain/usecase/UploadVideoUseCase.kt) | Kotlin | 13 | 0 | 2 | 15 |
| [src/main/kotlin/com/housing/movie/features/user/domain/entity/Role.kt](/src/main/kotlin/com/housing/movie/features/user/domain/entity/Role.kt) | Kotlin | 5 | 0 | 1 | 6 |
| [src/main/kotlin/com/housing/movie/features/user/domain/entity/User.kt](/src/main/kotlin/com/housing/movie/features/user/domain/entity/User.kt) | Kotlin | 7 | 22 | 2 | 31 |
| [src/main/kotlin/com/housing/movie/utils/CustomLogger.kt](/src/main/kotlin/com/housing/movie/utils/CustomLogger.kt) | Kotlin | 3 | 0 | 1 | 4 |
| [src/main/kotlin/com/housing/movie/utils/DateTimeHelper.kt](/src/main/kotlin/com/housing/movie/utils/DateTimeHelper.kt) | Kotlin | 32 | 0 | 9 | 41 |
| [src/main/kotlin/com/housing/movie/utils/StringHelper.kt](/src/main/kotlin/com/housing/movie/utils/StringHelper.kt) | Kotlin | 19 | 0 | 11 | 30 |

[summary](results.md)