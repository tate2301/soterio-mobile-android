package zw.co.guava.soterio.ui.main.getinfo.hospitals

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.db.entity.EntityHospital
import zw.co.guava.soterio.db.repo.RepoHospitals

class HospitalViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: RepoHospitals
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<EntityHospital>>

    init {
        val daoHospitals = CoreDatabase.getDatabase(application).daoHospitals()
        repository = RepoHospitals(daoHospitals)
        allWords = repository.allHospitals
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun saveHospitals(hospitals: List<EntityHospital>) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveAllHospitals(hospitals)
    }
}