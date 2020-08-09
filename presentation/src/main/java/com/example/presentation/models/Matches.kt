package com.example.presentation.models

import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.example.common.utils.glide.GlideApp
import com.example.common.utils.glide.SvgSoftwareLayerSetter
import com.example.presentation.R
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Data classes for Matches
 */
data class MatchResponse(
    var matches: List<Match> = ArrayList(),
    var errorMessage: String = ""
)

@Parcelize
data class Match(
    var id: Long = 0L,
    var competition: SubTeams? = null,
    var season: Season = Season(),
    var utcDate: String = "",
    var status: String? = "",
    var matchday: Int = 0,
    var stage: String = "",
    var group: String = "",
    var lastUpdated: String = "",
    var score: Score = Score(),
    var homeTeam: SubTeams = SubTeams(),
    var awayTeam: SubTeams = SubTeams()
) : Parcelable

@Parcelize
data class Season(
    var id: Long = 0L,
    var startDate: String? = "",
    var endDate: String? = ""
) : Parcelable

@Parcelize
data class Score(
    var fullTime: SubScores = SubScores(),
    var halfTime: SubScores = SubScores(),
    var duration: String = ""
) : Parcelable

@Parcelize
data class SubScores(
    var homeTeam: Int? = 0,
    var awayTeam: Int? = 0
) : Parcelable

@Parcelize
data class SubTeams(
    var id: Long = 0L,
    var name: String = ""
) : Parcelable

/**
 * Data classes for Competitions
 */
data class CompetitionResponse(
    var competitions: List<Competitions> = ArrayList(),
    var errorMessage: String = ""
)

@Parcelize
data class Competitions(
    var id: Long = 0L,
    var name: String = "",
    var currentSeason: Season = Season()
) : Parcelable

/**
 * Data classes for Teams
 */
data class TeamResponse(
    var teams: List<Team> = ArrayList(),
    var errorMessage: String = ""
)

data class Team(
    var id: Long = 0L,
    var name: String = "",
    var shortName: String = "",
    var crestUrl: String? = ""
)

/**
 * Data classes for Players
 */
@Parcelize
data class PlayerResponse(
    var id: Long = 0L,
    var name: String = "",
    var shortName: String = "",
    var crestUrl: String? = "",
    var squad: List<Player> = ArrayList(),
    var errorMessage: String = ""
): Parcelable

@Parcelize
data class Player(
    var id: Long = 0L,
    var name: String = "",
    var position: String? = "",
    var role: String = "",
    var count: Int = 0
): Parcelable

/**
 * Data classes for Standings
 */
data class StandingResponse(
    var standings: List<Standing> = ArrayList(),
    var errorMessage: String = ""
)

data class Standing(
    var table: List<Table> = ArrayList()
)

data class Table(
    var position: Int = 0,
    var team: Team = Team(),
    var playedGames: Int = 0,
    var points: Int = 0,
    var goalDifference: Int = 0
)

/**
 * Binding Adapters
 */
@BindingAdapter("crestUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    if (imageUrl.endsWith(".svg")) {
        GlideApp.with(view.context)
            .`as`(PictureDrawable::class.java)
            .placeholder(R.drawable.soccer)
            .error(R.drawable.soccer)
            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
            .load(Uri.parse(imageUrl))
            .into(view)
    } else {
        GlideApp.with(view.context)
            .load(imageUrl)
            .placeholder(R.drawable.soccer)
            .error(R.drawable.soccer)
            .transition(withCrossFade())
            .into(view)
    }
}

