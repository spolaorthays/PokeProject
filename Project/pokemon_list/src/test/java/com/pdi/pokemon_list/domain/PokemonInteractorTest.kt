package com.pdi.pokemon_list.domain

import com.pdi.network.data.PokemonResponse
import com.pdi.pokemon_list.data.local.PokemonRepository
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.PokemonDetails
import com.pdi.pokemon_list.data.remote.PokemonSpecies
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

class PokemonInteractorTest {

    @MockK
    private lateinit var repository: PokemonRepository

    private lateinit var interactor: PokemonContract.Interactor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        interactor = PokemonInteractor(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should get pokemonList from repository`() {
        val response = mockk<PokemonResponse<Pokemon>>()
        val results = mockk<List<Pokemon>>()

        every { repository.getPokemonListFromService(10, 0) } returns Single.just(response)
        every { response.results } returns results

        interactor.getPokemonListFromRepository(10, 0)
                .test()
                .assertValue(results)
                .assertComplete()
                .dispose()
    }

    @Test
    fun `should get pokemonDetails from repository`() {
        val response = mockk<PokemonDetails>()

        every { repository.getPokemonDetailsFromService("url") } returns Single.just(response)

        interactor.getPokemonDetailsFromRepository("url")
                .test()
                .assertValue(response)
                .assertComplete()
                .dispose()
    }

    @Test
    fun `should get pokemonColor from repository`() {
        val response = mockk<PokemonSpecies>()

        every { repository.getPokemonColorFromService("url") } returns Single.just(response)

        interactor.getPokemonColorRepository("url")
                .test()
                .assertValue(response)
                .assertComplete()
                .dispose()
    }
}