# Run AFTER the backend is started:  powershell -ExecutionPolicy Bypass -File .\test-api.ps1
param([string]$Base = "http://localhost:8080", [string]$AdminKey = $env:ADMIN_API_KEY)
$ErrorActionPreference = "Stop"
function Step($t){ Write-Host "`n== $t" -ForegroundColor Cyan }

Step "1. Health (pings MongoDB Atlas)"
Invoke-RestMethod "$Base/api/health"

Step "2. Catalog"
Invoke-RestMethod "$Base/api/crackers" | ConvertTo-Json -Depth 3
Invoke-RestMethod "$Base/api/colors"   | ConvertTo-Json -Depth 3

Step "3. Create wish"
$body = @{ name="Test User"; character="Sparkler"; sweet="Mysore Pak"; food="Idli" } | ConvertTo-Json
$wish = Invoke-RestMethod "$Base/api/wishes" -Method Post -ContentType "application/json" -Body $body
$wish

Step "4. Read wish back"
Invoke-RestMethod "$Base/api/wishes/$($wish.id)"

Step "5. Validation error (expect 400)"
try { Invoke-RestMethod "$Base/api/wishes" -Method Post -ContentType "application/json" -Body '{"name":""}' }
catch { Write-Host "Got $($_.Exception.Response.StatusCode.value__) as expected" }

Step "6. Log cracker play"
Invoke-RestMethod "$Base/api/crackers/plays" -Method Post -ContentType "application/json" -Body '{"crackerId":"sky","colorId":"gold"}'
try { Invoke-RestMethod "$Base/api/crackers/plays" -Method Post -ContentType "application/json" -Body '{"crackerId":"nope","colorId":"gold"}' }
catch { Write-Host "Unknown cracker rejected with $($_.Exception.Response.StatusCode.value__) as expected" }

Step "7. Stats"
Invoke-RestMethod "$Base/api/stats" | ConvertTo-Json -Depth 3

Step "8. Admin list without key (expect 403)"
try { Invoke-RestMethod "$Base/api/wishes" } catch { Write-Host "Got $($_.Exception.Response.StatusCode.value__) as expected" }

if ($AdminKey) {
  Step "9. Admin list + delete"
  Invoke-RestMethod "$Base/api/wishes" -Headers @{ "X-Admin-Key" = $AdminKey }
  Invoke-RestMethod "$Base/api/wishes/$($wish.id)" -Method Delete -Headers @{ "X-Admin-Key" = $AdminKey }
  Write-Host "Deleted test wish"
}
Write-Host "`nAll checks finished." -ForegroundColor Green
