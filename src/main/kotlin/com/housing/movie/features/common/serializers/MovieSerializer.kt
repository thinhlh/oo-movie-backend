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

            val genreIds: Array<String> =
                movie?.genres?.map { genre -> genre.id.toString() }?.toTypedArray() ?: emptyArray()

            jsonGenerator.writeFieldName("genreIds")
            jsonGenerator.writeArray(genreIds, 0, genreIds.size)

            jsonGenerator.writeFieldName("detail")
            defaultSerializer?.serialize(movie, jsonGenerator, serializerProvider)

            jsonGenerator.writeEndObject()
        }
    }

}