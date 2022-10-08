package com.stardeux.upprime.search.repository

import com.google.gson.Gson
import com.stardeux.upprime.BuildConfig
import com.stardeux.upprime.network.amazon.di.AMAZON_NAMED_QUALIFIER
import com.stardeux.upprime.search.repository.api.AmazonSearchApi
import com.stardeux.upprime.search.repository.api.SearchMediaContainerResponse
import com.stardeux.upprime.search.repository.model.AmazonSearchMediaMapper
import com.stardeux.upprime.search.repository.model.AmazonSearchRequest
import com.stardeux.upprime.search.usecase.model.AmazonSearchResultContainer
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.inject

class AmazonSearchRepository(
    private val amazonSearchApi: AmazonSearchApi,
    private val amazonSearchMediaMapper: AmazonSearchMediaMapper
) {

    suspend fun search(
        amazonSearchRequest: AmazonSearchRequest, country: String
    ): AmazonSearchResultContainer {
        return if (BuildConfig.DEBUG && false) {
            val results =
                "{\"Count\": 8561,\"Results\": [{\"Title\": \"A Trip to the Moon\",\"ASIN\": \"B07DPF8HJV\",\"imdbID\": \"tt0000417\",\"DateAdded\": \"2020-02-25T11:22:30.677\",\"Year\": 1902,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Magic Roses\",\"ASIN\": \"B07W7FG234\",\"imdbID\": \"tt0298525\",\"DateAdded\": \"2020-05-02T00:02:07.46\",\"Year\": 1906,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Frog\",\"ASIN\": \"B07TXRH2NS\",\"imdbID\": \"tt1821497\",\"DateAdded\": \"2020-05-02T00:02:48.413\",\"Year\": 1908,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Last Cartridge, an Incident of the Sepoy Rebellion in India\",\"ASIN\": \"B0140SUNM2\",\"imdbID\": \"tt1840952\",\"DateAdded\": \"2020-05-02T00:02:48.413\",\"Year\": 1908,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"1776, or The Hessian Renegades\",\"ASIN\": \"B07M7ZG11H\",\"imdbID\": \"tt0000895\",\"DateAdded\": \"2020-02-25T13:03:10.397\",\"Year\": 1909,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Frankenstein\",\"ASIN\": \"B079SQY8VD\",\"imdbID\": \"tt0001223\",\"DateAdded\": \"2020-02-25T13:03:30.82\",\"Year\": 1910,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Men Haters Club\",\"ASIN\": \"B086D7M3MG\",\"imdbID\": \"tt1882152\",\"DateAdded\": \"2020-04-27T00:03:18.383\",\"Year\": 1910,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Across the Mexican Line\",\"ASIN\": \"B01MZ85B3M\",\"imdbID\": \"tt0372119\",\"DateAdded\": \"2020-02-25T13:03:51.337\",\"Year\": 1911,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Three Million Dollars\",\"ASIN\": \"B0752X194K\",\"imdbID\": \"tt0001925\",\"DateAdded\": \"2020-02-25T13:03:51.337\",\"Year\": 1911,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Making of a Man\",\"ASIN\": \"B01N237Q11\",\"imdbID\": \"tt0001769\",\"DateAdded\": \"2020-02-25T13:03:51.35\",\"Year\": 1911,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"When the Tables Turned\",\"ASIN\": \"B085W2HJVC\",\"imdbID\": \"tt0274214\",\"DateAdded\": \"2020-03-18T13:04:47.213\",\"Year\": 1911,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"From the Manger to the Cross\",\"ASIN\": \"B002SHW7JA\",\"imdbID\": \"tt0002199\",\"DateAdded\": \"2020-01-04T01:03:51.14\",\"Year\": 1912,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Fatherhood of Buck McGee\",\"ASIN\": \"B0791NH7W6\",\"imdbID\": \"tt0348611\",\"DateAdded\": \"2020-02-25T13:04:11.803\",\"Year\": 1912,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Firing of the Patchwork Quilt\",\"ASIN\": \"B018YCSDK4\",\"imdbID\": \"tt0001330\",\"DateAdded\": \"2020-02-25T13:04:11.803\",\"Year\": 1912,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Just Maine Folks\",\"ASIN\": \"B077GBKQDH\",\"imdbID\": \"tt0364451\",\"DateAdded\": \"2020-02-25T13:04:11.82\",\"Year\": 1912,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Unusual Honeymoon\",\"ASIN\": \"B074KR11BG\",\"imdbID\": \"tt0368394\",\"DateAdded\": \"2020-02-25T13:04:11.837\",\"Year\": 1912,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Victoria Cross\",\"ASIN\": \"B06XCBP5FM\",\"imdbID\": \"tt0002562\",\"DateAdded\": \"2020-02-25T13:04:11.837\",\"Year\": 1912,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Land Beyond the Sunset\",\"ASIN\": \"B06WLKZM5C\",\"imdbID\": \"tt0000488\",\"DateAdded\": \"2020-02-25T13:04:11.85\",\"Year\": 1912,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"A Severe Test\",\"ASIN\": \"B016Q1MLJY\",\"imdbID\": \"tt1389556\",\"DateAdded\": \"2020-02-25T13:04:32.273\",\"Year\": 1913,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Inside of the White Slave Traffic\",\"ASIN\": \"B07DL58162\",\"imdbID\": \"tt0003016\",\"DateAdded\": \"2020-02-25T13:04:32.287\",\"Year\": 1913,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"An Old Man's Love Story\",\"ASIN\": \"B01N4R66BF\",\"imdbID\": \"tt0003235\",\"DateAdded\": \"2020-02-25T13:04:32.287\",\"Year\": 1913,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"A Florida Enchantment\",\"ASIN\": \"B01EZ9E2PU\",\"imdbID\": \"tt0003973\",\"DateAdded\": \"2020-02-25T13:04:52.693\",\"Year\": 1914,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Typhoon\",\"ASIN\": \"B01MUYHGQU\",\"imdbID\": \"tt0004740\",\"DateAdded\": \"2020-01-04T01:04:21.797\",\"Year\": 1914,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Patchwork Girl of Oz\",\"ASIN\": \"B000GW10U6\",\"imdbID\": \"tt0004457\",\"DateAdded\": \"2020-01-04T01:04:21.797\",\"Year\": 1914,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Little Country Mouse\",\"ASIN\": \"B073RWWDLJ\",\"imdbID\": \"tt0004235\",\"DateAdded\": \"2020-02-25T13:04:52.71\",\"Year\": 1914,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"A Helpful (?) Sisterhood\",\"ASIN\": \"B07H43NCXC\",\"imdbID\": \"tt0004065\",\"DateAdded\": \"2020-02-25T13:04:52.727\",\"Year\": 1914,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Sawdust and Salome\",\"ASIN\": \"B07DKW1K8R\",\"imdbID\": \"tt0004562\",\"DateAdded\": \"2020-02-25T13:04:52.727\",\"Year\": 1914,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Egyptian Mummy\",\"ASIN\": \"B01N0ZZXOT\",\"imdbID\": \"tt0003876\",\"DateAdded\": \"2020-02-25T13:04:52.74\",\"Year\": 1914,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Cheat\",\"ASIN\": \"B000W4MNIQ\",\"imdbID\": \"tt0005078\",\"DateAdded\": \"2020-01-04T01:04:42.203\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Birth of a Nation\",\"ASIN\": \"B005DWASMK\",\"imdbID\": \"tt0004972\",\"DateAdded\": \"2020-01-04T01:04:42.217\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Alice in Wonderland\",\"ASIN\": \"B01IH7EGNE\",\"imdbID\": \"tt0004873\",\"DateAdded\": \"2020-01-04T01:04:42.217\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Young Romance\",\"ASIN\": \"B000W4VDG4\",\"imdbID\": \"tt0006327\",\"DateAdded\": \"2020-01-04T01:04:42.217\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"A Fool There Was\",\"ASIN\": \"B01MQR5YNY\",\"imdbID\": \"tt0005339\",\"DateAdded\": \"2020-01-04T01:04:42.217\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Mystery of Dead Man's Isle\",\"ASIN\": \"B0182ZZGVW\",\"imdbID\": \"tt0319903\",\"DateAdded\": \"2020-02-25T13:05:13.163\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Billy the Bear Tamer\",\"ASIN\": \"B071G3ZBT7\",\"imdbID\": \"tt0004968\",\"DateAdded\": \"2020-02-25T13:05:13.177\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Doll-House Mystery\",\"ASIN\": \"B072HFTZFK\",\"imdbID\": \"tt0442148\",\"DateAdded\": \"2020-02-25T13:05:13.193\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Cabman Kate\",\"ASIN\": \"B01N6QY5IP\",\"imdbID\": \"tt0421917\",\"DateAdded\": \"2020-02-25T13:05:13.21\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Dust of Egypt\",\"ASIN\": \"B0836BDJSH\",\"imdbID\": \"tt0005232\",\"DateAdded\": \"2020-02-25T13:05:13.227\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Smoking Out of Bella Butts\",\"ASIN\": \"B084DFCQ7B\",\"imdbID\": \"tt0773104\",\"DateAdded\": \"2020-02-25T13:05:13.227\",\"Year\": 1915,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Intolerance: Love's Struggle Throughout the Ages\",\"ASIN\": \"B018SIJ6DW\",\"imdbID\": \"tt0006864\",\"DateAdded\": \"2020-01-04T01:05:02.67\",\"Year\": 1916,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Battle of the Somme\",\"ASIN\": \"B01DWPFZ5Y\",\"imdbID\": \"tt0006405\",\"DateAdded\": \"2020-01-04T01:05:02.763\",\"Year\": 1916,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Joan the Woman\",\"ASIN\": \"B01LAQQ8R6\",\"imdbID\": \"tt0008150\",\"DateAdded\": \"2020-01-04T01:05:02.857\",\"Year\": 1916,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Wife and Auto Trouble\",\"ASIN\": \"B002V6P6U0\",\"imdbID\": \"tt0007567\",\"DateAdded\": \"2020-02-25T13:05:33.693\",\"Year\": 1916,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Everett True Breaks Into the Movies\",\"ASIN\": \"B01A99E8PE\",\"imdbID\": \"tt6528876\",\"DateAdded\": \"2020-02-25T13:05:33.693\",\"Year\": 1916,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Dancer\",\"ASIN\": \"B07MRBT25D\",\"imdbID\": \"tt0430971\",\"DateAdded\": \"2020-02-25T13:05:33.71\",\"Year\": 1916,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Battle of the Ancre and Advance of the Tanks\",\"ASIN\": \"B00YR6YBE4\",\"imdbID\": \"tt1453259\",\"DateAdded\": \"2020-01-04T01:05:23.873\",\"Year\": 1917,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Eye of Envy\",\"ASIN\": \"B01C6D1UJI\",\"imdbID\": \"tt0194860\",\"DateAdded\": \"2020-02-25T13:05:54.147\",\"Year\": 1917,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Somebody Lied\",\"ASIN\": \"B01MRBRH2U\",\"imdbID\": \"tt0326184\",\"DateAdded\": \"2020-02-25T13:05:54.163\",\"Year\": 1917,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Tough Luck and Tin Lizzies\",\"ASIN\": \"B071J2QTXV\",\"imdbID\": \"tt0277391\",\"DateAdded\": \"2020-02-25T13:05:54.163\",\"Year\": 1917,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Station Content\",\"ASIN\": \"B074Q2HGN6\",\"imdbID\": \"tt0009651\",\"DateAdded\": \"2020-01-04T01:05:44.31\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Stella Maris\",\"ASIN\": \"B01LAQNTXW\",\"imdbID\": \"tt0009652\",\"DateAdded\": \"2020-01-04T01:05:44.31\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Hearts of the World\",\"ASIN\": \"B0161141O2\",\"imdbID\": \"tt0009150\",\"DateAdded\": \"2020-01-04T01:05:44.327\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Tarzan of the Apes\",\"ASIN\": \"B07FPVGHX6\",\"imdbID\": \"tt0009682\",\"DateAdded\": \"2020-01-04T01:05:44.343\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"A Society Sensation\",\"ASIN\": \"B073HKRY46\",\"imdbID\": \"tt0009629\",\"DateAdded\": \"2020-01-04T01:05:44.343\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Among the Cannibal Isles of the South Pacific\",\"ASIN\": \"B06WV8NK5N\",\"imdbID\": \"tt0008832\",\"DateAdded\": \"2020-01-04T01:05:44.343\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Married Virgin\",\"ASIN\": \"B01LARIPB2\",\"imdbID\": \"tt0009356\",\"DateAdded\": \"2020-01-04T01:05:44.357\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Spying the Spy\",\"ASIN\": \"B079FJPLC5\",\"imdbID\": \"tt0235777\",\"DateAdded\": \"2020-02-25T13:06:14.663\",\"Year\": 1918,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Smashing Barriers\",\"ASIN\": \"B071GMCFF8\",\"imdbID\": \"tt0010708\",\"DateAdded\": \"2020-01-04T01:06:04.763\",\"Year\": 1919,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"A Romance of Happy Valley\",\"ASIN\": \"B07MG55NSH\",\"imdbID\": \"tt0009559\",\"DateAdded\": \"2020-01-04T01:06:04.763\",\"Year\": 1919,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Broken Blossoms\",\"ASIN\": \"B07HQFSVTL\",\"imdbID\": \"tt0009968\",\"DateAdded\": \"2020-04-27T00:06:23.083\",\"Year\": 1919,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"True Heart Susie\",\"ASIN\": \"B073SHLG2G\",\"imdbID\": \"tt0010806\",\"DateAdded\": \"2020-04-27T00:06:23.13\",\"Year\": 1919,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Das Cabinet des Dr. Caligari\",\"ASIN\": \"B075RW6HSM\",\"imdbID\": \"tt0010323\",\"DateAdded\": \"2020-01-04T01:06:25.153\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Way Down East\",\"ASIN\": \"B07HH69WL7\",\"imdbID\": \"tt0011841\",\"DateAdded\": \"2020-01-04T01:06:25.17\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Der Golem, wie er in die Welt kam\",\"ASIN\": \"B078X54YX1\",\"imdbID\": \"tt0011237\",\"DateAdded\": \"2020-01-04T01:06:25.187\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Dr. Jekyll and Mr. Hyde\",\"ASIN\": \"B07YMMHQ6V\",\"imdbID\": \"tt0011130\",\"DateAdded\": \"2020-01-04T01:06:25.187\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Last of the Mohicans\",\"ASIN\": \"B002QXEK46\",\"imdbID\": \"tt0011387\",\"DateAdded\": \"2020-01-04T01:06:25.2\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Love Flower\",\"ASIN\": \"B01613KTKA\",\"imdbID\": \"tt0011415\",\"DateAdded\": \"2020-01-04T01:06:25.2\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Within Our Gates\",\"ASIN\": \"B01C6D1CS2\",\"imdbID\": \"tt0011870\",\"DateAdded\": \"2020-01-04T01:06:25.2\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Sex\",\"ASIN\": \"B01IH6V4DK\",\"imdbID\": \"tt0011679\",\"DateAdded\": \"2020-01-04T01:06:25.217\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The County Fair\",\"ASIN\": \"B01LAQQERU\",\"imdbID\": \"tt0011076\",\"DateAdded\": \"2020-01-04T01:06:25.217\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Nomads of the North\",\"ASIN\": \"B000YDOSJM\",\"imdbID\": \"tt0011513\",\"DateAdded\": \"2020-01-04T01:06:25.217\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Dr. Jekyll and Mr. Hyde\",\"ASIN\": \"B07W1674Z2\",\"imdbID\": \"tt0011131\",\"DateAdded\": \"2020-01-04T01:06:25.233\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Headin' Home\",\"ASIN\": \"B00B99UFLA\",\"imdbID\": \"tt0011267\",\"DateAdded\": \"2020-02-09T21:06:27.78\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Mark of Zorro\",\"ASIN\": \"B00U50AUYG\",\"imdbID\": \"tt0011439\",\"DateAdded\": \"2020-04-02T22:06:58.853\",\"Year\": 1920,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Kid\",\"ASIN\": \"B01LAQRBL8\",\"imdbID\": \"tt0012349\",\"DateAdded\": \"2020-01-04T01:06:45.623\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Orphans of the Storm\",\"ASIN\": \"B07MCMB5DX\",\"imdbID\": \"tt0012532\",\"DateAdded\": \"2020-01-04T01:06:45.637\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Three Musketeers\",\"ASIN\": \"B002SIZJJY\",\"imdbID\": \"tt0012752\",\"DateAdded\": \"2020-01-04T01:06:45.637\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Affairs of Anatol\",\"ASIN\": \"B01LAQOF44\",\"imdbID\": \"tt0011909\",\"DateAdded\": \"2020-01-04T01:06:45.653\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Love Light\",\"ASIN\": \"B018SIJTNE\",\"imdbID\": \"tt0012408\",\"DateAdded\": \"2020-01-04T01:06:45.653\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Beau Revel\",\"ASIN\": \"B072FVJD9T\",\"imdbID\": \"tt0011953\",\"DateAdded\": \"2020-01-04T01:06:45.653\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Miss Rovel\",\"ASIN\": \"B07D45DCM4\",\"imdbID\": \"tt0011471\",\"DateAdded\": \"2020-01-04T01:06:45.67\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Blasphemer\",\"ASIN\": \"B009C5JJZQ\",\"imdbID\": \"tt0221024\",\"DateAdded\": \"2020-01-04T01:06:45.67\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Behold the Man\",\"ASIN\": \"B004W2SFKY\",\"imdbID\": \"tt0221011\",\"DateAdded\": \"2020-02-25T13:07:16.303\",\"Year\": 1921,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Nosferatu, eine Symphonie des Grauens\",\"ASIN\": \"B0788F3GZK\",\"imdbID\": \"tt0013442\",\"DateAdded\": \"2020-03-11T03:08:34.757\",\"Year\": 1922,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Prisoner of Zenda\",\"ASIN\": \"B01KN1HGW0\",\"imdbID\": \"tt0013515\",\"DateAdded\": \"2020-01-04T01:07:06.48\",\"Year\": 1922,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Headless Horseman\",\"ASIN\": \"B01F2H71ZW\",\"imdbID\": \"tt0013223\",\"DateAdded\": \"2020-02-25T13:07:36.74\",\"Year\": 1922,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"My Wife's Relations\",\"ASIN\": \"B002ZXDI4U\",\"imdbID\": \"tt0013422\",\"DateAdded\": \"2020-02-25T13:07:36.757\",\"Year\": 1922,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Day Dreams\",\"ASIN\": \"B002WJP9L2\",\"imdbID\": \"tt0013055\",\"DateAdded\": \"2020-02-25T13:07:36.77\",\"Year\": 1922,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"One Exciting Night\",\"ASIN\": \"B01C6ECOYW\",\"imdbID\": \"tt0013458\",\"DateAdded\": \"2020-01-27T23:07:13.213\",\"Year\": 1922,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Extra Girl\",\"ASIN\": \"B004D1HVNG\",\"imdbID\": \"tt0014029\",\"DateAdded\": \"2020-01-04T01:07:27.34\",\"Year\": 1923,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Hunchback of Notre Dame\",\"ASIN\": \"B001NP5M68\",\"imdbID\": \"tt0014142\",\"DateAdded\": \"2020-05-02T00:06:55.237\",\"Year\": 1923,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Great Outdoors\",\"ASIN\": \"B07QMY8J4W\",\"imdbID\": \"tt0248050\",\"DateAdded\": \"2020-02-25T13:07:57.223\",\"Year\": 1923,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Soilers\",\"ASIN\": \"B01MZFF92O\",\"imdbID\": \"tt0011722\",\"DateAdded\": \"2020-02-25T13:07:57.24\",\"Year\": 1923,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Pioneer Trails\",\"ASIN\": \"B07STFX1QC\",\"imdbID\": \"tt0014359\",\"DateAdded\": \"2020-02-25T13:07:57.257\",\"Year\": 1923,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Marriage Circle\",\"ASIN\": \"B07M6LHJW7\",\"imdbID\": \"tt0015119\",\"DateAdded\": \"2020-01-04T01:07:47.823\",\"Year\": 1924,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"\$50,000 Reward\",\"ASIN\": \"B01M5840DL\",\"imdbID\": \"tt0014639\",\"DateAdded\": \"2020-01-04T01:07:47.84\",\"Year\": 1924,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Thief of Bagdad\",\"ASIN\": \"B01C6D0WJW\",\"imdbID\": \"tt0015400\",\"DateAdded\": \"2020-04-27T00:08:06.127\",\"Year\": 1924,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"He Who Gets Slapped\",\"ASIN\": \"B083JNJ324\",\"imdbID\": \"tt0014972\",\"DateAdded\": \"2020-01-27T23:07:54.54\",\"Year\": 1924,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"Shore Leave\",\"ASIN\": \"B01IH8FEZ2\",\"imdbID\": \"tt0016346\",\"DateAdded\": \"2020-01-04T01:08:09.48\",\"Year\": 1925,\"Country\": \"US\",\"Type\": \"Movie\"},{\"Title\": \"The Phantom of the Opera\",\"ASIN\": \"B001J9U7XG\",\"imdbID\": \"tt0016220\",\"DateAdded\": \"2020-01-04T01:08:09.48\",\"Year\": 1925,\"Country\": \"US\",\"Type\": \"Movie\"}]}"
            val gson: Gson by inject(Gson::class.java, named(AMAZON_NAMED_QUALIFIER))
            val parsed = gson.fromJson(results, SearchMediaContainerResponse::class.java)
            amazonSearchMediaMapper.mapToAmazonSearchResult(parsed)
        } else {
            amazonSearchMediaMapper.mapToAmazonSearchResult(
                amazonSearchApi.search(
                    amazonSearchRequest.title,
                    country,
                    amazonSearchMediaMapper.mapToApiValue(amazonSearchRequest.mediaTypeFilter),
                    amazonSearchRequest.yearStart,
                    amazonSearchRequest.yearEnd,
                    amazonSearchRequest.page
                )
            )
        }


    }

}