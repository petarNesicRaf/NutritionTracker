package rs.raf.vezbe11.data.models

import androidx.room.TypeConverters
import rs.raf.vezbe11.data.models.converters.SaveEntityConverter
import rs.raf.vezbe11.data.models.converters.WeekConverter


data class Week(
    @TypeConverters( SaveEntityConverter::class)
    val monday:List<String>?,
    @TypeConverters( SaveEntityConverter::class)
    val tuesday:List<String>?,
    @TypeConverters( SaveEntityConverter::class)
    val wednesday:List<String>?,
    @TypeConverters( SaveEntityConverter::class)
    val thursday:List<String>?,
    @TypeConverters( SaveEntityConverter::class)
    val friday:List<SaveEntity>?,
    @TypeConverters( SaveEntityConverter::class)
    val saturday:List<SaveEntity>?,
    @TypeConverters( SaveEntityConverter::class)
    val sunday:List<SaveEntity>?
)
