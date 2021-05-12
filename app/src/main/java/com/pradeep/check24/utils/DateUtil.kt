package com.pradeep.check24.utils

class DateUtil{


    companion object{

        fun getDateTime(timestamp: Long): String? {
            val sdf = java.text.SimpleDateFormat("dd.MM.yyyy")
            val date = java.util.Date(timestamp * 1000)
            return  sdf.format(date)
        }

    }

}

