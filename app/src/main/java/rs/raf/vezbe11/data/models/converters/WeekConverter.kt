package rs.raf.vezbe11.data.models.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.KoinComponent
import org.koin.core.get
import rs.raf.vezbe11.data.models.SaveEntity
import rs.raf.vezbe11.data.models.Week

class WeekConverter : KoinComponent {
    private val jsonAdapter: JsonAdapter<Week>

    init {
        val type = Types.newParameterizedType(Week::class.java)
        val moshi: Moshi = get()
        jsonAdapter = moshi.adapter(type)
    }

    @TypeConverter
    fun fromJson(json: String?): Week? {
        return json?.let {
            return jsonAdapter.fromJson(json)
        }
    }

    @TypeConverter
    fun toJson(week: Week?): String? {
        return week?.let {
            return jsonAdapter.toJson(week)
        }
    }
}