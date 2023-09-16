package rs.raf.vezbe11.data.models.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.KoinComponent
import org.koin.core.get
import java.lang.reflect.Type

class ListConverter: KoinComponent {
    private val jsonAdapter: JsonAdapter<List<String>>

    init {
        val type: Type = Types.newParameterizedType(MutableList::class.java, String::class.java)
        val moshi: Moshi = get()
        jsonAdapter = moshi.adapter(type)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.let {
            return jsonAdapter.toJson(list)
        }
    }

    @TypeConverter
    fun toList(json: String?): List<String>? {
        return json?.let {
            return jsonAdapter.fromJson(json)
        }
    }
}