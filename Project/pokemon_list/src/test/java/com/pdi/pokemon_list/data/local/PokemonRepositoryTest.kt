package com.pdi.pokemon_list.data.local

import com.pdi.network.data.PokemonResponse
import com.pdi.pokemon_list.data.remote.Pokemon
import com.pdi.pokemon_list.data.remote.PokemonDetails
import com.pdi.pokemon_list.data.remote.PokemonSpecies
import com.pdi.pokemon_list.data.service.PokemonService
import com.pdi.pokemon_list.domain.PokemonContract
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

class PokemonRepositoryTest {

    @MockK
    private lateinit var service: PokemonService

    private lateinit var repository: PokemonContract.Repository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository = PokemonRepository(service)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should get pokemonList from service`() {
        val response = mockk<PokemonResponse<Pokemon>>()

        every { service.getPokemon(10, 0) } returns Single.just(response)

        repository.getPokemonListFromService(10, 0)
                .test()
                .assertValue(response)
                .assertComplete()
                .dispose()
    }

    @Test
    fun `should get pokemonDetails from service`() {
        val response = mockk<PokemonDetails>()

        every { service.getPokemonDetails("url") } returns Single.just(response)

        repository.getPokemonDetailsFromService("url")
                .test()
                .assertValue(response)
                .assertComplete()
                .dispose()
    }

    @Test
    fun `should get pokemonColor from service`() {
        val response = mockk<PokemonSpecies>()

        every { service.getPokemonColor("url") } returns Single.just(response)

        repository.getPokemonColorFromService("url")
                .test()
                .assertValue(response)
                .assertComplete()
                .dispose()
    }

}