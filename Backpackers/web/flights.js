$(window).on('load', getFlights());

function getFlights() {
    $.ajax({
        type: 'GET',
        url: "/flights",
        success: function (data) {
            $(document).ready(function () {
                $('#flightTable').tablesort();
                for (var i = 0; i < data.length; i++) {
                    var days = [];
                    for (var j = 0; j < 7; j++) {
                        days[j] = parseInt(data[i].days.charAt(j)) ? "\"large green checkmark icon\"" : "\"large red remove icon\"";
                    }

                    $('#flightTable tbody').append('<tr>\n' +
                        '        <td class="center aligned">' + data[i].flightNo + '</td>\n' +
                        '        <td class="center aligned">' + data[i].origin + '</td>\n' +
                        '        <td class="center aligned">' + data[i].destination + '</td>\n' +
                        '        <td class="center aligned">' + data[i].duration + '</td>\n' +
                        '        <td class="center aligned">' + data[i].noOfDays + '</td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[0] + '></i>\n' +
                        '        </td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[1] + '></i>\n' +
                        '        </td>\n' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[2] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[3] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[4] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[5] + '></i>\n' +
                        '        </td>\n' + '' +
                        '        <td class="center aligned">\n' +
                        '            <i class=' + days[6] + '></i>\n' +
                        '        </td>\n' +
                        '    </tr>');
                }
            });
        }
    });
}


function filter() {
    var input, filter, table, tr;
    input = document.getElementById("filter");
    filter = input.value.toUpperCase();
    table = document.getElementById("flightTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (var i = 0; i < tr.length; i++) {
        var src = tr[i].getElementsByTagName("td")[1];
        var dest = tr[i].getElementsByTagName("td")[2];
        if (src || dest) {
            if (src.innerHTML.toUpperCase().indexOf(filter) > -1 || dest.innerHTML.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

(function ($) {
    $.tablesort = function ($table, settings) {
        var self = this;
        this.$table = $table;
        this.$thead = this.$table.find('thead');
        this.settings = $.extend({}, $.tablesort.defaults, settings);
        this.$sortCells = this.$thead.length > 0 ? this.$thead.find('th:not(.no-sort)') : this.$table.find('th:not(.no-sort)');
        this.$sortCells.on('click.tablesort', function () {
            self.sort($(this));
        });
        this.index = null;
        this.$th = null;
        this.direction = null;
    };

    $.tablesort.prototype = {

        sort: function (th, direction) {
            var start = new Date(),
                self = this,
                table = this.$table,
                rowsContainer = table.find('tbody').length > 0 ? table.find('tbody') : table,
                rows = rowsContainer.find('tr').has('td, th'),
                cells = rows.find(':nth-child(' + (th.index() + 1) + ')').filter('td, th'),
                sortBy = th.data().sortBy,
                sortedMap = [];

            var unsortedValues = cells.map(function (idx, cell) {
                if (sortBy)
                    return (typeof sortBy === 'function') ? sortBy($(th), $(cell), self) : sortBy;
                return ($(this).data().sortValue != null ? $(this).data().sortValue : $(this).text());
            });
            if (unsortedValues.length === 0) return;

            //click on a different column
            if (this.index !== th.index()) {
                this.direction = 'asc';
                this.index = th.index();
            }
            else if (direction !== 'asc' && direction !== 'desc')
                this.direction = this.direction === 'asc' ? 'desc' : 'asc';
            else
                this.direction = direction;

            direction = this.direction == 'asc' ? 1 : -1;

            self.$table.trigger('tablesort:start', [self]);
            self.log("Sorting by " + this.index + ' ' + this.direction);

            // Try to force a browser redraw
            self.$table.css("display");
            // Run sorting asynchronously on a timeout to force browser redraw after
            // `tablesort:start` callback. Also avoids locking up the browser too much.
            setTimeout(function () {
                self.$sortCells.removeClass(self.settings.asc + ' ' + self.settings.desc);
                for (var i = 0, length = unsortedValues.length; i < length; i++) {
                    sortedMap.push({
                        index: i,
                        cell: cells[i],
                        row: rows[i],
                        value: unsortedValues[i]
                    });
                }

                sortedMap.sort(function (a, b) {
                    return self.settings.compare(a.value, b.value) * direction;
                });

                $.each(sortedMap, function (i, entry) {
                    rowsContainer.append(entry.row);
                });

                th.addClass(self.settings[self.direction]);

                self.log('Sort finished in ' + ((new Date()).getTime() - start.getTime()) + 'ms');
                self.$table.trigger('tablesort:complete', [self]);
                //Try to force a browser redraw
                self.$table.css("display");
            }, unsortedValues.length > 2000 ? 200 : 10);
        },

        log: function (msg) {
            if (($.tablesort.DEBUG || this.settings.debug) && console && console.log) {
                console.log('[tablesort] ' + msg);
            }
        },

        destroy: function () {
            this.$sortCells.off('click.tablesort');
            this.$table.data('tablesort', null);
            return null;
        }

    };

    $.tablesort.DEBUG = false;

    $.tablesort.defaults = {
        debug: $.tablesort.DEBUG,
        asc: 'sorted ascending',
        desc: 'sorted descending',
        compare: function (a, b) {
            if (a > b) {
                return 1;
            } else if (a < b) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    $.fn.tablesort = function (settings) {
        var table, sortable, previous;
        return this.each(function () {
            table = $(this);
            previous = table.data('tablesort');
            if (previous) {
                previous.destroy();
            }
            table.data('tablesort', new $.tablesort(table, settings));
        });
    };

})(window.Zepto || window.jQuery);

