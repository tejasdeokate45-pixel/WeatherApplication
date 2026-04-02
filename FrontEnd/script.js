async function getForecast() {
    const city = document.getElementById("city").value.trim();
    const days = document.getElementById("days").value;

    if (!city) {
        alert("Please enter a city name");
        return;
    }

    const url = `http://localhost:8080/weather/forecast?city=${encodeURIComponent(city)}&days=${days}`;
    const container = document.getElementById("forecast");

    // Loading state
    container.innerHTML = `
        <div class="loading-wrap">
            <div class="spinner"></div>
            <p>Fetching weather for <strong>${city}</strong>…</p>
        </div>
    `;

    try {
        const res = await fetch(url);
        if (!res.ok) throw new Error("Bad response");
        const data = await res.json();
        displayForecast(data);
    } catch (error) {
        container.innerHTML = `
            <div class="error-box">
                <div class="err-icon">⚠️</div>
                <p>Could not fetch weather data. Please check the city name and try again.</p>
            </div>
        `;
    }
}

// Press Enter to search
document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("city").addEventListener("keydown", (e) => {
        if (e.key === "Enter") getForecast();
    });
});

function getWeatherIcon(condition) {
    if (!condition) return "🌤️";
    const c = condition.toLowerCase();
    if (c.includes("thunder") || c.includes("storm"))  return "⛈️";
    if (c.includes("snow") || c.includes("blizzard"))  return "❄️";
    if (c.includes("rain") || c.includes("drizzle"))   return "🌧️";
    if (c.includes("mist") || c.includes("fog"))       return "🌫️";
    if (c.includes("overcast"))                        return "☁️";
    if (c.includes("cloud") || c.includes("patchy"))   return "⛅";
    if (c.includes("sun") || c.includes("clear"))      return "☀️";
    return "🌤️";
}

function formatDate(dateStr) {
    // dateStr format: "2026-04-01"
    const date = new Date(dateStr + "T00:00:00");
    const days  = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
    const months= ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
    return {
        day:   days[date.getDay()],
        date:  date.getDate(),
        month: months[date.getMonth()]
    };
}

function getTempColor(temp) {
    if (temp >= 38) return "#ff4d4d";
    if (temp >= 32) return "#fbbf24";
    if (temp >= 24) return "#34d399";
    return "#60a5fa";
}

function displayForecast(data) {
    const container = document.getElementById("forecast");
    container.innerHTML = "";

    const current = data.weatherResponse;
    const icon    = getWeatherIcon(current.condition);
    const tempColor = getTempColor(current.temperature);

    // ── Main Current Weather Card ──
    const mainCard = document.createElement("div");
    mainCard.className = "main-card";
    mainCard.innerHTML = `
        <div class="main-left">
            <div class="main-city">${current.city}</div>
            <div class="main-region">${current.region}, ${current.county}</div>
            <div class="main-condition">${icon} ${current.condition}</div>
            <div class="main-feels">Feels like a warm day</div>
        </div>
        <div class="main-right">
            <div class="main-icon">${icon}</div>
            <div class="main-temp" style="background: linear-gradient(135deg,#fff,${tempColor}); -webkit-background-clip:text; background-clip:text; -webkit-text-fill-color:transparent;">
                ${current.temperature}°C
            </div>
        </div>
    `;
    container.appendChild(mainCard);

    // ── Day Cards Row ──
    if (data.daysTemp && data.daysTemp.length > 0) {
        const row = document.createElement("div");
        row.className = "days-row";

        data.daysTemp.forEach((day, i) => {
            const { day: dayName, date, month } = formatDate(day.Date);
            const dayIcon = getWeatherIcon(current.condition);
            const isToday = i === 0;

            const card = document.createElement("div");
            card.className = "day-card" + (isToday ? " today" : "");
            card.style.animationDelay = `${i * 0.08}s`;
            card.innerHTML = `
                <div class="day-date">${isToday ? "Today" : dayName}</div>
                <div class="day-subdate">${date} ${month}</div>
                <div class="day-icon">${dayIcon}</div>
                <div class="day-avg">${day.avgTemp}°</div>
                <div class="day-minmax">
                    <span class="day-min">↓ ${day.minTemp}°</span>
                    <span class="day-max">↑ ${day.maxTemp}°</span>
                </div>
            `;
            row.appendChild(card);
        });

        container.appendChild(row);
    }
}