package rs.raf.vezbe11.data.models.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.KoinComponent
import org.koin.core.get
import rs.raf.vezbe11.data.models.SaveEntity


class SaveEntityConverter : KoinComponent {
    private val jsonAdapter: JsonAdapter<SaveEntity>
    //daily
    init {
        val type = Types.newParameterizedType(List::class.java, SaveEntity::class.java)
        val moshi: Moshi = get()
        jsonAdapter = moshi.adapter(type)
    }

    @TypeConverter
    fun fromJson(json: String?): SaveEntity? {
        return json?.let {
            return jsonAdapter.fromJson(json)
        }
    }

    @TypeConverter
    fun toJson(saveEntity: SaveEntity?): String? {
        return saveEntity?.let {
            return jsonAdapter.toJson(saveEntity)
        }
    }
}