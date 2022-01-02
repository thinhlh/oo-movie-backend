package com.housing.movie.features.common.serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier
import com.housing.movie.base.BaseSerializer
import com.housing.movie.features.movie.domain.entity.Movie
import org.springframework.boot.jackson.JsonComponent


class MovieBeanSerializerModifier : BeanSerializerModifier() {
    override fun modifySerializer(
        config: SerializationConfig?,
        beanDesc: BeanDescription?,
        serializer: JsonSerializer<*>?
    ): JsonSerializer<*> {
        if (beanDesc?.beanClass?.equals(Movie::class) == true) {
            return MovieSerializer(serializer as JsonSerializer<Movie>?)
        }

        return serializer!!
    }
}

@JsonComponent
class MovieSerializer(
    override val defaultSerializer: JsonSerializer<Movie>? = null
) : BaseSerializer<Movie>() {


    override fun serialize(movie: Movie?, jsonGenerator: JsonGenerator?, serializerProvider: SerializerProvider?) {

        jsonGenerator?.let {
            jsonGenerator.writeStartObject()

//            val genreIds: Array<String> =
//                movie?.genres?.map { genre -> genre.id.toString() }?.toTypedArray() ?: emptyArray()
//
//            jsonGenerator.writeFieldName("genreIds")
//            jsonGenerator.writeArray(genreIds, 0, genreIds.size)
//
//            jsonGenerator.writeFieldName("detail")
//            defaultSerializer?.serialize(movie, jsonGenerator, serializerProvider)


            it.writeStringField("id", movie?.id.toString())
            it.writeObjectField("episodes", movie?.episodes)
            it.writeObjectField("genres", movie?.genres)


            val movieDetail = movie?.movieDetail
            it.writeBooleanField("adult", movieDetail?.adult ?: true)
            it.writeStringField("back_drop_path", movieDetail?.backdrop_path)
            it.writeNumberField("budget", movieDetail?.budget ?: 0)
            it.writeStringField("homepage", movieDetail?.homepage)
            it.writeStringField("original_language", movieDetail?.originalLanguage)
            it.writeStringField("original_title", movieDetail?.originalTitle)
            it.writeStringField("overview", movieDetail?.overview)
            it.writeStringField("poster_path", movieDetail?.posterPath)
            it.writeObjectField("release_date", movieDetail?.releaseDate)
            it.writeNumberField("revenue", movieDetail?.revenue ?: 0)
            it.writeStringField("title", movieDetail?.title)
            it.writeObjectField("vote_average", movieDetail?.voteAverage)
            it.writeObjectField("vote_count", movieDetail?.voteCount)
            it.writeObjectField("view_count", movieDetail?.viewCount)
            it.writeBooleanField("is_mine", movieDetail?.isMine ?: true)
            it.writeBooleanField("is_tv_series", movieDetail?.isTVSeries ?: false)

            it.writeBooleanField("enabled", movie?.enabled ?: true)

            jsonGenerator.writeEndObject()
        }
    }

}