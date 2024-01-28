package com.m.snowalert.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.m.snowalert.R
import com.m.snowalert.dashboard.model.DashboardScreenState
import com.m.snowalert.ui.theme.SnowAlertTheme

@Composable
fun DashboardScreen(
    dashboardState: DashboardScreenState,
    onAddressClick: () -> Unit,
    onAlertSwitchClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "Snow Alert",
                fontWeight = Bold,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 22.sp,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_settings_24),
                modifier = Modifier.align(Alignment.CenterEnd),
                contentDescription = "Settings",
                alignment = Alignment.CenterEnd,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Spacer(
            modifier = Modifier
                .height(1.dp)
                .background(Color.Gray)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = dashboardState.address,
            modifier = Modifier.fillMaxWidth(),
            fontSize = 24.sp,
            fontWeight = Bold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier
                .clip(CircleShape)
                .border(3.dp, Color.Blue, CircleShape)
                .size(200.dp)
                .align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "60%",
                modifier = Modifier
                    .padding(top = 40.dp),
                fontWeight = Bold,
                fontSize = 48.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = "chance of snow on",
                textAlign = TextAlign.Center,
            )
            Text(
                text = dashboardState.forecastInfo.forecastDate,
                fontWeight = Bold,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Snow alert")
                Switch(
                    checked = dashboardState.alertInfo.enabled,
                    onCheckedChange = { onAlertSwitchClick.invoke() }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Notify at")
                Text(dashboardState.alertInfo.notifyAt)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Next alert in")
                Text(dashboardState.alertInfo.nextAlertInMinutes.toString())
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Duration")
                Text(dashboardState.alertInfo.alertWatchdogDuration.name)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Repeat every")
                Text(dashboardState.alertInfo.repeatInterval.name)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    SnowAlertTheme {
        DashboardScreen(
            dashboardState = DashboardScreenState(address = "Line 2, Frankfurt"),
            onAddressClick = {},
            onAlertSwitchClick = {},
        )
    }
}