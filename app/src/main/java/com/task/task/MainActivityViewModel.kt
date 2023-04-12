package com.task.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.task.task.di.RetroServiceInterface
import com.task.task.model.RecyclerData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    @Inject
    lateinit var mService: RetroServiceInterface
    private lateinit var liveDataList: MutableLiveData<List<RecyclerData>>

    init {
        (application as MyApplication).getRetoComponent().inject(this)
        liveDataList = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<RecyclerData>> {
        return liveDataList
    }

    fun makeApiCall() {
        val call: Call<List<RecyclerData>?> = mService.getDataFromAPI()
        call.enqueue(object : Callback<List<RecyclerData>?> {
            override fun onResponse(
                call: Call<List<RecyclerData>?>,
                response: Response<List<RecyclerData>?>
            ) {
                if (response.isSuccessful) {
                    liveDataList.postValue(response.body())
                } else {
                    liveDataList.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<RecyclerData>?>, t: Throwable) {
                liveDataList.postValue(null)
            }

        })
    }
}