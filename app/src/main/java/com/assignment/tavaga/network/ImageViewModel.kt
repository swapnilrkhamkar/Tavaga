package com.assignment.tavaga.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.tavaga.client.APIClient
import com.assignment.tavaga.model.Image
import com.assignment.tavaga.urlInterface.APIInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ImageViewModel : ViewModel() {
    private lateinit var sources: MutableLiveData<Resource<ArrayList<Image>>>
    private lateinit var compositeDisposable: CompositeDisposable

    fun getImages(): LiveData<Resource<ArrayList<Image>>> {
        sources = MutableLiveData()
        compositeDisposable = CompositeDisposable()
        loadSources()
        return sources
    }

    private fun loadSources() {
        val apiService = APIClient.getClient().create(APIInterface::class.java)
        val call = apiService.getImages()

        compositeDisposable.add(
            call.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse, this::handleError)
        )

    }

    private fun handleResponse(companyList: ArrayList<Image>) {
        Log.e("JBN|SBH ", "res " + companyList)

        if (companyList != null) {
            sources.postValue(Resource.success(companyList))
        }
    }

    private fun handleError(error: Throwable) {
        Log.e("JBN|SBH ", " " + error)
        sources.postValue(Resource.error(error, null))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

