package rs.raf.vezbe11.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import rs.raf.vezbe11.data.models.converters.ListConverter
import rs.raf.vezbe11.data.models.converters.PlanEntityConverter
import rs.raf.vezbe11.data.models.converters.SaveEntityConverter
import rs.raf.vezbe11.data.models.converters.WeekConverter

@Entity(tableName = "plans")
data class PlanEntity (
    @PrimaryKey
    val strPlanName:String,

    val weekStartDate: String?, // Start date of the week
    //primary key da bude iz edit text iz fragmenta za dodavanje novog plana
    @TypeConverters( ListConverter::class)
    val monday:List<String>?,
    @TypeConverters( ListConverter::class)
    val tuesday:List<String>?,
    @TypeConverters( ListConverter::class)
    val wednesday:List<String>?,
    @TypeConverters( ListConverter::class)
    val thursday:List<String>?,
    @TypeConverters( ListConverter::class)
    val friday:List<String>?,
    @TypeConverters( ListConverter::class)
    val saturday:List<String>?,
    @TypeConverters( ListConverter::class)
    val sunday:List<String>?
)