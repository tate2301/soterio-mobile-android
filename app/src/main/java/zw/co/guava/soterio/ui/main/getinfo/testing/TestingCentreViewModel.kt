package zw.co.guava.soterio.ui.main.getinfo.testing

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import zw.co.guava.soterio.db.CoreDatabase
import zw.co.guava.soterio.db.entity.EntityTestingCentre
import zw.co.guava.soterio.db.repo.RepoTestingCentres

class TestingCentreViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: RepoTestingCentres
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allTestingCentres: LiveData<List<EntityTestingCentre>>

    init {
        val daoTestingCentres = CoreDatabase.getDatabase(application).daoTestingCentres()
        repository = RepoTestingCentres(daoTestingCentres)
        allTestingCentres = repository.allTestingCentres
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun saveTestingCentres(TestingCentres: List<EntityTestingCentre>) = viewModelScope.launch(Dispatchers.IO) {
        repository.saveAllTestingCentres(TestingCentres)
    }
}