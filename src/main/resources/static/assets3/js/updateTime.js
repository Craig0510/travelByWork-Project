// 更新日期和時間
function updateDateTime() {
    const currentDateTimeElement = document.getElementById('currentDateTime');
    const currentDate = new Date();

       // 格式化日期和時間，例如：August 21, 2023 15:30:45
       const formattedDateTime = `${currentDate.toLocaleDateString('en-US', {
    month: 'long',
    day: 'numeric',
    year: 'numeric'
    })} ${currentDate.toLocaleTimeString('en-US', {
    hour12: true,
    hour: 'numeric',
    minute: 'numeric',
    second: 'numeric'
    })}`;

        currentDateTimeElement.textContent = formattedDateTime;
    }

    // 初始設定和每秒更新一次
       updateDateTime();
       setInterval(updateDateTime, 1000);