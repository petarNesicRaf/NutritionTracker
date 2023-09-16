package rs.raf.vezbe11.data.models.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import org.koin.core.KoinComponent
import org.koin.core.get
import rs.raf.vezbe11.data.models.PlanEntity

class PlanEntityConverter : KoinComponent {
    private val jsonAdapter: JsonAdapter<PlanEntity>

    init {
        val type = Types.newParameterizedType(PlanEntity::class.java)
        val moshi: Moshi = get()
        jsonAdapter = moshi.adapter(type)
    }

    @TypeConverter
    fun fromJson(json: String?): PlanEntity? {
        return json?.let {
            return jsonAdapter.fromJson(json)
        }
    }

    @TypeConverter
    fun toJson(planEntity: PlanEntity?): String? {
        return planEntity?.let {
            return jsonAdapter.toJson(planEntity)
        }
    }
}