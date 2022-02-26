package ru.kirea.pressureandpulsefirestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.kirea.pressureandpulsefirestore.databinding.MainActivityBinding
import ru.kirea.pressureandpulsefirestore.windows.main.MainFragment

class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by lazy { MainActivityBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, MainFragment())
            fragmentTransaction.commit()
        }
    }
}